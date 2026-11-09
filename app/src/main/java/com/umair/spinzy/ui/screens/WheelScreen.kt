package com.umair.spinzy.ui.screens

import android.view.HapticFeedbackConstants
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DeleteSweep
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Numbers
import androidx.compose.material.icons.rounded.Percent
import androidx.compose.material.icons.rounded.PlaylistRemove
import androidx.compose.material.icons.rounded.Restore
import androidx.compose.material.icons.rounded.ThumbsUpDown
import androidx.compose.material.icons.rounded.Balance
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker1D
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.umair.spinzy.audio.LocalSfx
import com.umair.spinzy.audio.Sfx
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.game.ToastTone
import com.umair.spinzy.game.UiEvent
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.ui.components.ConfettiRain
import com.umair.spinzy.ui.components.GlassCard
import com.umair.spinzy.ui.components.GlassTextField
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.IconBadge
import com.umair.spinzy.ui.components.LocalToaster
import com.umair.spinzy.ui.components.OnShake
import com.umair.spinzy.ui.components.SpinzyDialog
import com.umair.spinzy.ui.components.SpinzyTopBar
import com.umair.spinzy.ui.components.Squircle
import com.umair.spinzy.ui.components.SquircleShape
import com.umair.spinzy.ui.components.WheelFace
import com.umair.spinzy.ui.components.WheelPointer
import com.umair.spinzy.ui.components.WheelRim
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.components.rememberMorphingSlices
import com.umair.spinzy.ui.components.shakeOn
import com.umair.spinzy.ui.components.sliceAtPointer
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.GlassStroke
import com.umair.spinzy.ui.theme.Midnight
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.Plum900
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine
import com.umair.spinzy.wheel.AddResult
import com.umair.spinzy.wheel.WheelColors
import com.umair.spinzy.wheel.WheelItem
import com.umair.spinzy.wheel.WheelState
import com.umair.spinzy.wheel.WheelViewModel
import com.umair.spinzy.wheel.wheelTextIsLight
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.roundToInt
import kotlin.random.Random

private val WheelEasing = CubicBezierEasing(0.12f, 0.8f, 0.2f, 1f)

@Composable
fun WheelScreen(state: GameState, vm: GameViewModel, wvm: WheelViewModel = viewModel()) {
    val s = LocalStrings.current
    val w by wvm.state.collectAsStateWithLifecycle()
    val toaster = LocalToaster.current
    val sfx = LocalSfx.current
    val view = LocalView.current
    val scope = rememberCoroutineScope()
    val haptics by rememberUpdatedState(state.settings.haptics)

    val slices = rememberMorphingSlices(w)
    val slicesNow by rememberUpdatedState(slices)
    val rotation = remember { Animatable(0f) }
    val flap = remember { Animatable(0f) }
    val bump = remember { Animatable(1f) }
    var spinning by remember { mutableStateOf(false) }
    var input by rememberSaveable { mutableStateOf("") }
    var shake by remember { mutableStateOf(0) }
    var confirmClear by remember { mutableStateOf(false) }

    // tick + pointer flap whenever a slice edge passes the pointer
    LaunchedEffect(Unit) {
        snapshotFlow { sliceAtPointer(slicesNow, rotation.value) }.distinctUntilChanged().collect {
            if (!spinning) return@collect
            sfx?.play(Sfx.TICK, 0.55f, 0.9f + Random.nextFloat() * 0.2f)
            if (haptics) view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
            launch { flap.snapTo(-26f); flap.animateTo(0f, spring(dampingRatio = 0.3f, stiffness = Spring.StiffnessMedium)) }
        }
    }

    fun spin(velocity: Float) {
        if (spinning || !w.canSpin || w.winner != null) {
            if (!w.canSpin) { shake++; toaster.show(ToastTone.WARNING) { it.wheelEmpty } }
            return
        }
        spinning = true
        sfx?.play(Sfx.WHOOSH, 0.8f)
        scope.launch {
            val idx = wvm.pickWinnerIndex()
            val slice = slices[idx]
            val theta = slice.start + slice.sweep * (0.15f + Random.nextFloat() * 0.7f)
            val dir = if (velocity >= 0) 1f else -1f
            val speed = abs(velocity).coerceIn(900f, 4200f)
            val spins = (speed / 600f).coerceIn(3f, 8f).roundToInt()
            val cur = rotation.value
            val delta = if (dir > 0) (-theta - cur).mod(360f) else -((cur + theta).mod(360f))
            val target = cur + delta + dir * spins * 360f
            val duration = (abs(target - cur) * 6.67f / speed * 1000f).toInt().coerceIn(3000, 7000)
            rotation.animateTo(target + dir * 3f, tween(duration, easing = WheelEasing))
            rotation.animateTo(target, spring(dampingRatio = 0.35f, stiffness = Spring.StiffnessLow))
            sfx?.play(Sfx.LAND)
            if (haptics) view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            spinning = false
            wvm.onLanded(idx)
            sfx?.play(Sfx.WIN, 0.8f)
        }
    }
    OnShake(state.settings.shakeToSpin && !spinning && w.winner == null) { d -> spin((2200f + Random.nextFloat() * 1800f) * d) }

    fun add() {
        when (wvm.add(input)) {
            AddResult.EMPTY -> { shake++; toaster.show(ToastTone.WARNING) { it.typeItemFirst } }
            AddResult.LIMIT -> toaster.show(ToastTone.WARNING) { it.maxItems }
            AddResult.ADDED -> {
                val name = input.trim()
                input = ""
                sfx?.play(Sfx.POP, 0.6f, 1.2f)
                toaster.show(ToastTone.SUCCESS) { it.itemAdded(name) }
                scope.launch { bump.snapTo(1.06f); bump.animateTo(1f, spring(dampingRatio = 0.35f, stiffness = Spring.StiffnessMediumLow)) }
            }
        }
    }

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier.fillMaxSize().safeDrawingPadding().imePadding(),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item(key = "top") { SpinzyTopBar(s.spinWheel, s.itemsCount(w.items.size), onBack = { vm.back() }) }

            // ---------- the wheel ----------
            item(key = "wheel") {
                BoxWithConstraints(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    val size = minOf(maxWidth, 360.dp)
                    val tracker = remember { VelocityTracker1D(isDataDifferential = true) }
                    Box(
                        Modifier.size(size).graphicsLayer { scaleX = bump.value; scaleY = bump.value }
                            .pointerInput(Unit) {
                                val c = Offset(this.size.width / 2f, this.size.height / 2f)
                                var last = 0f
                                detectDragGestures(
                                    onDragStart = { p -> tracker.resetTracking(); last = angleDeg(p - c) },
                                    onDrag = { ch, _ ->
                                        if (spinning) return@detectDragGestures
                                        val a = angleDeg(ch.position - c)
                                        var d = a - last
                                        if (d > 180f) d -= 360f
                                        if (d < -180f) d += 360f
                                        last = a
                                        tracker.addDataPoint(ch.uptimeMillis, d)
                                        scope.launch { rotation.snapTo(rotation.value + d) }
                                    },
                                    onDragEnd = { val v = tracker.calculateVelocity(); if (abs(v) > 240f) spin(v) },
                                )
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        // drop shadow under the wheel
                        Box(Modifier.size(size * 0.94f).shadow(30.dp, CircleShape, ambientColor = Coral, spotColor = Coral))
                        WheelFace(slices, Modifier.size(size * 0.88f).graphicsLayer { rotationZ = rotation.value })
                        WheelRim(spinning, Modifier.size(size * 0.98f))
                        // hub = spin button
                        val hubPulse = rememberInfiniteTransition(label = "hub")
                        val pulse by hubPulse.animateFloat(1f, 1.07f, infiniteRepeatable(tween(900), androidx.compose.animation.core.RepeatMode.Reverse), label = "p")
                        Box(
                            Modifier.size(size * 0.24f)
                                .graphicsLayer { val k = if (!spinning && w.canSpin) pulse else 1f; scaleX = k; scaleY = k }
                                .shadow(18.dp, CircleShape, ambientColor = Wine, spotColor = Wine)
                                .clip(CircleShape)
                                .background(Brush.radialGradient(listOf(PeachLight, Peach, Coral)))
                                .border(4.dp, Wine, CircleShape)
                                .pressable(pressedScale = 0.88f) { spin(1500f + Random.nextFloat() * 1800f) },
                            contentAlignment = Alignment.Center,
                        ) {
                            BasicText(
                                s.spin.uppercase(), style = MaterialTheme.typography.labelLarge.copy(color = Wine, textAlign = TextAlign.Center),
                                maxLines = 1, autoSize = TextAutoSize.StepBased(minFontSize = 9.sp, maxFontSize = 18.sp),
                                modifier = Modifier.padding(horizontal = 8.dp),
                            )
                        }
                        WheelPointer(flap.value, Modifier.align(Alignment.TopCenter).offset(y = (-6).dp).size(size * 0.11f, size * 0.14f))
                        if (w.items.size < 2 && w.picked.isEmpty()) {
                            Text(
                                s.wheelEmpty, style = MaterialTheme.typography.titleMedium, color = TextMid, textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = size * 0.2f).width(size * 0.6f),
                            )
                        }
                    }
                }
            }

            item(key = "hint") {
                Text(
                    if (w.items.isEmpty() && w.picked.isNotEmpty()) s.allPicked else s.wheelHint,
                    style = MaterialTheme.typography.bodyMedium, color = TextMid, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),
                )
            }

            // ---------- modes ----------
            item(key = "modes") {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    ModeToggle(s.removeWinner, Icons.Rounded.PlaylistRemove, w.removeWinner, Modifier.weight(1f)) {
                        wvm.setRemoveWinner(!w.removeWinner)
                        toaster.show(ToastTone.INFO) { t -> if (!w.removeWinner) t.settingOn(t.removeWinner) else t.settingOff(t.removeWinner) }
                    }
                    ModeToggle(s.customOdds, Icons.Rounded.Percent, w.customOdds, Modifier.weight(1f)) {
                        wvm.setCustomOdds(!w.customOdds)
                        toaster.show(ToastTone.INFO) { t -> if (!w.customOdds) t.settingOn(t.customOdds) else t.settingOff(t.customOdds) }
                    }
                }
            }

            // ---------- input ----------
            item(key = "input") {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    GlassTextField(input, { input = it.take(24) }, s.addItemHint, Modifier.weight(1f).shakeOn(shake), onDone = { add() })
                    Spacer(Modifier.width(10.dp))
                    Box(
                        Modifier.size(56.dp).pressable(enabled = !spinning) { add() }
                            .background(Brush.linearGradient(listOf(PeachLight, Coral)), Squircle),
                        contentAlignment = Alignment.Center,
                    ) { Icon(Icons.Rounded.Add, s.addItemHint, tint = Wine) }
                }
            }

            // ---------- presets ----------
            item(key = "presets") {
                val players = state.players.map { it.name }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (players.size >= 2) item { PresetChip(s.presetPlayers, Icons.Rounded.Groups) { wvm.applyPreset(players); sfx?.play(Sfx.REVEAL, 0.5f) } }
                    item { PresetChip(s.presetYesNo, Icons.Rounded.ThumbsUpDown) { wvm.applyPreset(listOf(s.yes, s.no, s.maybe)); sfx?.play(Sfx.REVEAL, 0.5f) } }
                    item { PresetChip(s.presetNumbers, Icons.Rounded.Numbers) { wvm.applyPreset((1..10).map { "$it" }); sfx?.play(Sfx.REVEAL, 0.5f) } }
                    if (w.items.isNotEmpty() || w.picked.isNotEmpty()) item { PresetChip(s.clearAll, Icons.Rounded.DeleteSweep) { confirmClear = true } }
                }
            }

            // ---------- odds overview ----------
            if (w.customOdds && w.items.isNotEmpty()) {
                item(key = "odds") { OddsBar(w, s.splitEqually, s.totalPercent(w.typedTotal), if (w.typedTotal != 100) s.scaledNote else null) { wvm.splitEqually() } }
            }

            // ---------- items ----------
            items(w.items, key = { it.id }) { item ->
                ItemRow(
                    item, w, enabled = !spinning,
                    modifier = Modifier.animateItem(
                        fadeInSpec = spring(stiffness = Spring.StiffnessMediumLow),
                        placementSpec = spring(dampingRatio = 0.8f, stiffness = Spring.StiffnessMediumLow),
                        fadeOutSpec = tween(180),
                    ),
                    onColor = { wvm.cycleColor(item.id) },
                    onWeight = { wvm.setWeight(item.id, it) },
                    onDelete = {
                        val removed = wvm.remove(item.id) ?: return@ItemRow
                        toaster.show(UiEvent.Toast(ToastTone.INFO, { it.itemRemoved(removed.first.label) }, { it.undo }, { wvm.restore(removed.first, removed.second) }))
                    },
                )
            }

            // ---------- picked (elimination mode) ----------
            if (w.picked.isNotEmpty()) {
                item(key = "picked") { PickedSection(w.picked, s.picked, s.putBackAll) { wvm.putBackAll(); sfx?.play(Sfx.REVEAL, 0.6f) } }
            }
        }

        WinnerPopup(
            winner = w.winner, state = w,
            onOk = { remove -> wvm.dismissWinner(remove) },
        )
        SpinzyDialog(
            visible = confirmClear, icon = Icons.Rounded.DeleteSweep, destructive = true,
            title = s.clearWheelTitle, body = s.clearWheelBody, confirm = s.clearAll, dismiss = s.cancel,
            onConfirm = { confirmClear = false; wvm.clearAll() }, onDismiss = { confirmClear = false },
        )
    }
}

private fun angleDeg(v: Offset) = atan2(v.y, v.x) * 180f / PI.toFloat()

@Composable
private fun ModeToggle(label: String, icon: ImageVector, on: Boolean, modifier: Modifier, onClick: () -> Unit) {
    val bg by animateColorAsState(if (on) Peach else Cream.copy(alpha = 0.07f), label = "bg")
    val fg by animateColorAsState(if (on) Wine else TextMid, label = "fg")
    val check by animateFloatAsState(if (on) 1f else 0f, spring(dampingRatio = 0.45f), label = "chk")
    Row(
        modifier.height(48.dp).clip(RoundedCornerShape(50)).background(bg).border(1.dp, if (on) Peach else GlassStroke, RoundedCornerShape(50))
            .pressable(onClick = onClick).padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
    ) {
        Icon(icon, null, tint = fg, modifier = Modifier.size(18.dp).graphicsLayer { rotationZ = check * 360f })
        Spacer(Modifier.width(8.dp))
        Text(label, style = MaterialTheme.typography.labelLarge, color = fg, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun PresetChip(label: String, icon: ImageVector, onClick: () -> Unit) {
    GlassCard(Modifier.pressable(onClick = onClick), shape = RoundedCornerShape(50)) {
        Row(Modifier.padding(horizontal = 14.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Peach, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text(label, style = MaterialTheme.typography.labelLarge, color = TextHigh)
        }
    }
}

/** Stacked colour bar showing every slice's share + total + "split equally". */
@Composable
private fun OddsBar(w: WheelState, splitLabel: String, totalLabel: String, note: String?, onSplit: () -> Unit) {
    GlassCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp)) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(totalLabel, style = MaterialTheme.typography.titleMedium, color = if (note == null) PeachLight else Peach, modifier = Modifier.weight(1f))
                Row(Modifier.clip(RoundedCornerShape(50)).pressable(onClick = onSplit).padding(horizontal = 10.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.Balance, null, tint = Peach, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(splitLabel, style = MaterialTheme.typography.labelLarge, color = Peach)
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth().height(14.dp).clip(RoundedCornerShape(50))) {
                w.items.forEach { item ->
                    val f by animateFloatAsState(w.fraction(item).coerceAtLeast(0.001f), spring(dampingRatio = 0.8f), label = "f${item.id}")
                    Box(Modifier.weight(f).fillMaxSize().background(WheelColors[item.colorIndex % WheelColors.size]))
                }
            }
            if (note != null) {
                Spacer(Modifier.height(8.dp))
                Text(note, style = MaterialTheme.typography.bodyMedium, color = TextLow)
            }
        }
    }
}

@Composable
private fun ItemRow(
    item: WheelItem, w: WheelState, enabled: Boolean, modifier: Modifier,
    onColor: () -> Unit, onWeight: (Int) -> Unit, onDelete: () -> Unit,
) {
    val color = WheelColors[item.colorIndex % WheelColors.size]
    val swatch = remember(item.colorIndex) { Animatable(0.6f) }
    LaunchedEffect(item.colorIndex) { swatch.animateTo(1f, spring(dampingRatio = 0.35f)) }
    GlassCard(modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), borderColor = color.copy(alpha = 0.45f)) {
        Column(Modifier.padding(start = 12.dp, end = 6.dp, top = 10.dp, bottom = if (w.customOdds) 2.dp else 10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(40.dp).graphicsLayer { scaleX = swatch.value; scaleY = swatch.value }
                        .clip(Squircle).background(Brush.linearGradient(listOf(color, color.copy(alpha = 0.75f))))
                        .pressable(enabled = enabled, onClick = onColor),
                    contentAlignment = Alignment.Center,
                ) { Text(item.label.take(1).uppercase(), style = MaterialTheme.typography.titleMedium, color = if (wheelTextIsLight(item.colorIndex)) Cream else Plum900) }
                Spacer(Modifier.width(12.dp))
                Text(item.label, style = MaterialTheme.typography.titleMedium, color = TextHigh, modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                val pct by animateFloatAsState(w.percent(item).toFloat(), tween(300), label = "pct")
                Text(
                    "${pct.roundToInt()}%", style = MaterialTheme.typography.labelLarge, color = Plum900,
                    modifier = Modifier.clip(RoundedCornerShape(50)).background(color).padding(horizontal = 10.dp, vertical = 4.dp),
                )
                Box(Modifier.size(40.dp).pressable(enabled = enabled, onClick = onDelete), contentAlignment = Alignment.Center) {
                    Icon(Icons.Rounded.Close, null, tint = TextLow)
                }
            }
            AnimatedVisibility(w.customOdds, enter = expandVertically() + fadeIn(), exit = shrinkVertically() + fadeOut()) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 10.dp)) {
                    Slider(
                        value = item.weight.toFloat(), onValueChange = { onWeight(it.roundToInt()) },
                        valueRange = 1f..100f, enabled = enabled, modifier = Modifier.weight(1f),
                        colors = SliderDefaults.colors(thumbColor = color, activeTrackColor = color, inactiveTrackColor = Cream.copy(alpha = 0.12f)),
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("${item.weight}%", style = MaterialTheme.typography.labelLarge, color = TextMid, modifier = Modifier.width(44.dp), textAlign = TextAlign.End)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PickedSection(picked: List<WheelItem>, title: String, putBack: String, onPutBack: () -> Unit) {
    GlassCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp)) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title.uppercase(), style = MaterialTheme.typography.labelMedium, color = Peach, modifier = Modifier.weight(1f))
                Row(Modifier.clip(RoundedCornerShape(50)).pressable(onClick = onPutBack).padding(horizontal = 10.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.Restore, null, tint = Peach, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(putBack, style = MaterialTheme.typography.labelLarge, color = Peach)
                }
            }
            Spacer(Modifier.height(10.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                picked.forEachIndexed { i, it ->
                    val c = WheelColors[it.colorIndex % WheelColors.size]
                    Row(
                        Modifier.clip(RoundedCornerShape(50)).background(c.copy(alpha = 0.22f)).border(1.dp, c.copy(alpha = 0.5f), RoundedCornerShape(50))
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text("${i + 1}", style = MaterialTheme.typography.labelLarge, color = c)
                        Spacer(Modifier.width(6.dp))
                        Text(it.label, style = MaterialTheme.typography.labelLarge, color = TextHigh)
                    }
                }
            }
        }
    }
}

/** Game-style reward: spinning sun rays, confetti, trophy badge and the prize name popping in. */
@Composable
private fun WinnerPopup(winner: WheelItem?, state: WheelState, onOk: (remove: Boolean) -> Unit) {
    val s = LocalStrings.current
    var shown by remember { mutableStateOf<WheelItem?>(null) }
    var chance by remember { mutableStateOf(0) }
    if (winner != null && shown?.id != winner.id) { shown = winner; chance = state.percent(winner) }
    Box(Modifier.fillMaxSize()) {
        AnimatedVisibility(winner != null, enter = fadeIn(tween(250)), exit = fadeOut(tween(250))) {
            val item = shown ?: return@AnimatedVisibility
            val color = WheelColors[item.colorIndex % WheelColors.size]
            val t = rememberInfiniteTransition(label = "rays")
            val rays by t.animateFloat(0f, 360f, infiniteRepeatable(tween(14_000, easing = LinearEasing)), label = "r")
            val pop = remember(item.id) { Animatable(0f) }
            val title = remember(item.id) { Animatable(0f) }
            LaunchedEffect(item.id) {
                launch { pop.animateTo(1f, spring(dampingRatio = 0.5f, stiffness = Spring.StiffnessLow)) }
                kotlinx.coroutines.delay(220)
                title.animateTo(1f, spring(dampingRatio = 0.35f, stiffness = Spring.StiffnessMediumLow))
            }
            Box(
                Modifier.fillMaxSize().background(Midnight.copy(alpha = 0.88f))
                    .clickable(remember { MutableInteractionSource() }, null) {},
                contentAlignment = Alignment.Center,
            ) {
                Canvas(Modifier.fillMaxSize()) {
                    rotate(rays) {
                        val r = size.maxDimension
                        for (i in 0 until 16) drawArc(
                            Brush.radialGradient(listOf(color.copy(alpha = 0.35f), Color.Transparent), center = center, radius = r * 0.6f),
                            i * 22.5f, 11f, true, Offset(center.x - r, center.y - r), androidx.compose.ui.geometry.Size(r * 2, r * 2),
                        )
                    }
                }
                ConfettiRain(Modifier.fillMaxSize(), count = 110)
                Column(
                    Modifier.padding(28.dp).fillMaxWidth()
                        .graphicsLayer { scaleX = 0.6f + 0.4f * pop.value; scaleY = 0.6f + 0.4f * pop.value; alpha = pop.value.coerceIn(0f, 1f); rotationZ = (1f - pop.value) * -10f }
                        .shadow(40.dp, SquircleShape(5.0), ambientColor = color, spotColor = color)
                        .clip(SquircleShape(5.0))
                        .background(Brush.linearGradient(listOf(color, color.copy(alpha = 0.8f), Wine)))
                        .padding(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconBadge(Icons.Rounded.EmojiEvents, Modifier.graphicsLayer { rotationZ = (1f - title.value) * 30f }, size = 76.dp, bg = Wine, tint = PeachLight)
                    Spacer(Modifier.height(14.dp))
                    Text(s.winnerCaps, style = MaterialTheme.typography.labelMedium, color = Plum900.copy(alpha = 0.75f))
                    Spacer(Modifier.height(6.dp))
                    BasicText(
                        item.label,
                        style = MaterialTheme.typography.displayMedium.copy(color = if (wheelTextIsLight(item.colorIndex)) Cream else Plum900, textAlign = TextAlign.Center),
                        maxLines = 2, autoSize = TextAutoSize.StepBased(minFontSize = 20.sp, maxFontSize = 44.sp),
                        modifier = Modifier.fillMaxWidth().graphicsLayer { val k = 0.3f + 0.7f * title.value; scaleX = k; scaleY = k; alpha = title.value.coerceIn(0f, 1f) },
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(s.chanceOf(chance), style = MaterialTheme.typography.bodyLarge, color = Plum900.copy(alpha = 0.7f))
                    Spacer(Modifier.height(24.dp))
                    if (state.removeWinner) {
                        GlowButton(s.okRemove, colors = listOf(Wine, Plum900), textColor = PeachLight, modifier = Modifier.fillMaxWidth(), height = 56.dp) { onOk(true) }
                        Spacer(Modifier.height(6.dp))
                        Text(s.keepIt, style = MaterialTheme.typography.labelLarge, color = Plum900.copy(alpha = 0.8f),
                            modifier = Modifier.clip(RoundedCornerShape(50)).pressable { onOk(false) }.padding(horizontal = 20.dp, vertical = 12.dp))
                    } else {
                        GlowButton(s.ok, colors = listOf(Wine, Plum900), textColor = PeachLight, modifier = Modifier.fillMaxWidth(), height = 56.dp) { onOk(false) }
                    }
                }
            }
        }
    }
}

