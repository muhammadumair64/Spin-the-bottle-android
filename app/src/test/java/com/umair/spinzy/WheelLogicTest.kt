package com.umair.spinzy

import androidx.test.core.app.ApplicationProvider
import com.umair.spinzy.wheel.AddResult
import com.umair.spinzy.wheel.MAX_WHEEL_ITEMS
import com.umair.spinzy.wheel.WheelViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.random.Random

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class WheelLogicTest {
    private lateinit var vm: WheelViewModel
    private val st get() = vm.state.value

    @Before fun setUp() {
        vm = WheelViewModel(ApplicationProvider.getApplicationContext())
        // wait for the saved wheel to load so it can't overwrite the test setup
        var tries = 0
        while (!st.loaded && tries++ < 200) { org.robolectric.shadows.ShadowLooper.idleMainLooper(); Thread.sleep(10) }
        vm.clearAll()
        vm.setCustomOdds(false); vm.setRemoveWinner(false)
    }

    @Test fun addValidation() {
        assertEquals(AddResult.EMPTY, vm.add("  "))
        repeat(MAX_WHEEL_ITEMS) { assertEquals(AddResult.ADDED, vm.add("Item $it")) }
        assertEquals(AddResult.LIMIT, vm.add("one too many"))
    }

    @Test fun equalModeGivesEqualSlices() {
        listOf("A", "B", "C", "D").forEach { vm.add(it) }
        st.items.forEach { assertEquals(0.25f, st.fraction(it), 0.0001f) }
    }

    @Test fun customOddsRespectsPercentages() {
        listOf("A", "B", "C", "D", "E").forEach { vm.add(it) }
        vm.setCustomOdds(true)
        val ids = st.items.map { it.id }
        vm.setWeight(ids[0], 60); ids.drop(1).forEach { vm.setWeight(it, 10) }
        assertEquals(100, st.typedTotal)
        assertEquals(60, st.percent(st.items[0]))
        // weighted picks land on "A" about 60% of the time
        val rnd = Random(42)
        val hits = (1..5000).count { vm.pickWinnerIndex(rnd) == 0 }
        assertTrue("A picked $hits / 5000", hits in 2800..3200)
    }

    @Test fun splitEquallyAddsUpTo100() {
        listOf("A", "B", "C").forEach { vm.add(it) }
        vm.setCustomOdds(true)
        vm.splitEqually()
        assertEquals(100, st.typedTotal)
    }

    @Test fun eliminationRemovesWinnerAndKeeps100() {
        listOf("A", "B", "C", "D", "E").forEach { vm.add(it) }
        vm.setCustomOdds(true); vm.setRemoveWinner(true)
        val ids = st.items.map { it.id }
        vm.setWeight(ids[0], 60); ids.drop(1).forEach { vm.setWeight(it, 10) }
        vm.onLanded(0)
        assertEquals("A", st.winner?.label)
        vm.dismissWinner(remove = true)
        assertNull(st.winner)
        assertEquals(4, st.items.size)
        assertEquals(listOf("A"), st.picked.map { it.label })
        assertEquals("remaining odds re-scaled to 100%", 100, st.typedTotal)
        vm.putBackAll()
        assertEquals(5, st.items.size)
        assertTrue(st.picked.isEmpty())
    }

    @Test fun keepItLeavesWheelUntouched() {
        listOf("A", "B").forEach { vm.add(it) }
        vm.setRemoveWinner(true)
        vm.onLanded(1); vm.dismissWinner(remove = false)
        assertEquals(2, st.items.size)
    }

    @Test fun undoRestoresDeletedItem() {
        listOf("A", "B", "C").forEach { vm.add(it) }
        val (item, idx) = vm.remove(st.items[1].id)!!
        assertEquals(listOf("A", "C"), st.items.map { it.label })
        vm.restore(item, idx)
        assertEquals(listOf("A", "B", "C"), st.items.map { it.label })
    }
}
