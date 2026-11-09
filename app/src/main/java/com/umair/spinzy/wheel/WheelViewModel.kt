package com.umair.spinzy.wheel

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.umair.spinzy.data.SpinzyPrefs
import com.umair.spinzy.data.WheelSnapshot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.random.Random

const val MAX_WHEEL_ITEMS = 24

/** Bright, party-friendly slice colors (dark text works on all of them except the deep ones). */
val WheelColors = listOf(
    Color(0xFFF07068), Color(0xFFF8B38A), Color(0xFFFFD166), Color(0xFF7FD8BE), Color(0xFF7DB9F2),
    Color(0xFFB67BD0), Color(0xFFF2A3C7), Color(0xFFB0305E), Color(0xFFF59A76), Color(0xFF5FB3A6),
)

/** Slices whose color is dark enough to need light text. */
fun wheelTextIsLight(colorIndex: Int) = colorIndex % WheelColors.size in setOf(7, 9)

data class WheelItem(val id: Long, val label: String, val weight: Int = 10, val colorIndex: Int = 0)

data class WheelState(
    val items: List<WheelItem> = emptyList(),
    val picked: List<WheelItem> = emptyList(),
    val removeWinner: Boolean = false,
    val customOdds: Boolean = false,
    /** Winner waiting for the reward popup. */
    val winner: WheelItem? = null,
    val loaded: Boolean = false,
) {
    fun effectiveWeight(i: WheelItem) = if (customOdds) i.weight.coerceAtLeast(1) else 1
    val totalWeight: Int get() = items.sumOf { effectiveWeight(it) }
    /** Share of the wheel for each item, 0..1 (always sums to 1). */
    fun fraction(i: WheelItem): Float = if (totalWeight == 0) 0f else effectiveWeight(i).toFloat() / totalWeight
    fun percent(i: WheelItem): Int = (fraction(i) * 100).roundToInt()
    /** Sum of the raw percentages the user typed (custom mode). */
    val typedTotal: Int get() = items.sumOf { it.weight }
    val canSpin get() = items.size >= 2 || (items.size == 1 && removeWinner)
}

enum class AddResult { ADDED, EMPTY, LIMIT }

class WheelViewModel(app: Application) : AndroidViewModel(app) {
    private val prefs = SpinzyPrefs(app)
    private val _state = MutableStateFlow(WheelState())
    val state: StateFlow<WheelState> = _state.asStateFlow()
    private var nextId = 1L

    init {
        viewModelScope.launch {
            val snap = prefs.wheel.first()
            fun conv(l: List<Triple<String, Int, Int>>) = l.map { (a, w, c) -> WheelItem(nextId++, a, w, c) }
            _state.update {
                it.copy(items = conv(snap.items), picked = conv(snap.picked), removeWinner = snap.removeWinner, customOdds = snap.customOdds, loaded = true)
            }
        }
    }

    private fun persist() = viewModelScope.launch {
        val s = _state.value
        fun enc(l: List<WheelItem>) = l.map { Triple(it.label, it.weight, it.colorIndex) }
        prefs.saveWheel(WheelSnapshot(enc(s.items), enc(s.picked), s.removeWinner, s.customOdds))
    }

    private fun nextColor(): Int {
        val used = _state.value.items.map { it.colorIndex % WheelColors.size }
        // pick the least-used color, and never the same as the last slice
        val last = used.lastOrNull()
        return WheelColors.indices.filter { it != last }.minBy { c -> used.count { it == c } * 10 + c }
    }

    fun add(label: String): AddResult {
        val clean = label.trim().take(24)
        if (clean.isEmpty()) return AddResult.EMPTY
        if (_state.value.items.size >= MAX_WHEEL_ITEMS) return AddResult.LIMIT
        val w = if (_state.value.items.isEmpty()) 10 else (100 / (_state.value.items.size + 1)).coerceAtLeast(1)
        _state.update { it.copy(items = it.items + WheelItem(nextId++, clean, w, nextColor())) }
        persist()
        return AddResult.ADDED
    }

    /** Removes an item; returns it with its index so the UI can offer Undo. */
    fun remove(id: Long): Pair<WheelItem, Int>? {
        val idx = _state.value.items.indexOfFirst { it.id == id }
        if (idx < 0) return null
        val item = _state.value.items[idx]
        _state.update { it.copy(items = it.items.filterNot { i -> i.id == id }) }
        normalizeTo100()
        persist()
        return item to idx
    }

    fun restore(item: WheelItem, index: Int) {
        _state.update { s -> if (s.items.any { it.id == item.id }) s else s.copy(items = s.items.toMutableList().apply { add(index.coerceIn(0, size), item) }) }
        normalizeTo100()
        persist()
    }

    fun setWeight(id: Long, w: Int) {
        _state.update { s -> s.copy(items = s.items.map { if (it.id == id) it.copy(weight = w.coerceIn(1, 100)) else it }) }
        persist()
    }

    fun cycleColor(id: Long) {
        _state.update { s -> s.copy(items = s.items.map { if (it.id == id) it.copy(colorIndex = (it.colorIndex + 1) % WheelColors.size) else it }) }
        persist()
    }

    /** Equal shares that add up to exactly 100. */
    fun splitEqually() {
        val n = _state.value.items.size
        if (n == 0) return
        val base = 100 / n
        val extra = 100 - base * n
        _state.update { s -> s.copy(items = s.items.mapIndexed { i, it -> it.copy(weight = (base + if (i < extra) 1 else 0).coerceAtLeast(1)) }) }
        persist()
    }

    /** Custom mode: rescale typed % so they add up to 100 again (ratios are kept). */
    private fun normalizeTo100() {
        val s = _state.value
        if (!s.customOdds || s.items.isEmpty()) return
        val total = s.items.sumOf { it.weight }.coerceAtLeast(1)
        if (total == 100) return
        val scaled = s.items.map { (it.weight * 100.0 / total) }
        val floors = scaled.map { it.toInt().coerceAtLeast(1) }.toMutableList()
        var rest = 100 - floors.sum()
        val order = scaled.indices.sortedByDescending { scaled[it] - scaled[it].toInt() }
        var k = 0
        while (rest > 0 && order.isNotEmpty()) { floors[order[k % order.size]]++; rest--; k++ }
        _state.update { st -> st.copy(items = st.items.mapIndexed { i, it -> it.copy(weight = floors[i].coerceIn(1, 100)) }) }
    }

    fun setRemoveWinner(on: Boolean) { _state.update { it.copy(removeWinner = on) }; persist() }

    fun setCustomOdds(on: Boolean) {
        _state.update { it.copy(customOdds = on) }
        if (on && _state.value.typedTotal != 100) splitEqually() else persist()
    }

    fun applyPreset(labels: List<String>) {
        _state.update { it.copy(items = emptyList(), picked = emptyList()) }
        labels.take(MAX_WHEEL_ITEMS).forEach { add(it) }
        if (_state.value.customOdds) splitEqually()
    }

    fun clearAll() { _state.update { it.copy(items = emptyList(), picked = emptyList()) }; persist() }

    /** Weighted random pick — returns the index of the winning slice. */
    fun pickWinnerIndex(random: Random = Random): Int {
        val s = _state.value
        val total = s.totalWeight
        if (total <= 0) return 0
        var r = random.nextInt(total)
        s.items.forEachIndexed { i, it ->
            r -= s.effectiveWeight(it)
            if (r < 0) return i
        }
        return s.items.lastIndex
    }

    fun onLanded(index: Int) = _state.update { it.copy(winner = it.items.getOrNull(index)) }

    /** Closes the reward popup. In "remove winner" mode [remove] moves the item to the picked list. */
    fun dismissWinner(remove: Boolean) {
        val w = _state.value.winner ?: return
        _state.update {
            if (remove) it.copy(winner = null, items = it.items.filterNot { i -> i.id == w.id }, picked = it.picked + w)
            else it.copy(winner = null)
        }
        if (remove) normalizeTo100()
        persist()
    }

    fun putBackAll() {
        _state.update { it.copy(items = it.items + it.picked, picked = emptyList()) }
        normalizeTo100()
        persist()
    }
}
