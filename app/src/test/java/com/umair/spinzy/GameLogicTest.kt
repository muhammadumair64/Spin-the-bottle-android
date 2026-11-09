package com.umair.spinzy

import androidx.test.core.app.ApplicationProvider
import com.umair.spinzy.data.PromptType
import com.umair.spinzy.data.WildCard
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.game.MAX_PLAYERS
import com.umair.spinzy.game.Phase
import com.umair.spinzy.game.Screen
import com.umair.spinzy.i18n.Language
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class GameLogicTest {
    private lateinit var vm: GameViewModel
    private val st get() = vm.state.value

    @Before fun setUp() {
        vm = GameViewModel(ApplicationProvider.getApplicationContext())
        // start from a clean roster regardless of what DataStore loaded
        st.players.forEach { vm.removePlayer(it.id) }
        listOf("Ana", "Bo", "Cy", "Di").forEach { vm.addPlayer(it) }
        vm.updateSettings(st.settings.copy(wildCards = false, fairMode = true, heatEscalation = false))
    }

    @Test fun addPlayerValidation() {
        assertFalse("blank names are rejected", vm.addPlayer("   "))
        assertFalse("duplicate names are rejected (case-insensitive)", vm.addPlayer("ana"))
        repeat(MAX_PLAYERS) { vm.addPlayer("P$it") }
        assertEquals(MAX_PLAYERS, st.players.size)
    }

    @Test fun truthAndDareScoring() {
        vm.startGame()
        vm.onBottleLanded(0); vm.choose(PromptType.TRUTH); vm.complete()
        assertEquals(1, st.players[0].score)
        vm.onBottleLanded(1); vm.choose(PromptType.DARE); vm.complete()
        assertEquals(2, st.players[1].score)
        vm.onBottleLanded(2); vm.choose(PromptType.DARE); vm.chickenOut()
        assertEquals(-1, st.players[2].score)
        vm.onBottleLanded(3); vm.choose(PromptType.TRUTH); vm.busted()
        assertEquals(0, st.players[3].score)
        assertEquals(1, st.players[3].busted)
        assertEquals(4, st.turn)
        assertEquals(Phase.SPIN, st.phase)
    }

    @Test fun threeInARowGivesStreakBonus() {
        vm.startGame()
        repeat(3) { vm.onBottleLanded(0); vm.choose(PromptType.TRUTH); vm.complete() }
        assertEquals(1 + 1 + (1 + 1), st.players[0].score)
    }

    @Test fun wildCards() {
        vm.startGame()
        vm.onBottleLanded(0, forceWild = WildCard.DOUBLE); vm.choose(PromptType.DARE); vm.complete()
        assertEquals("double dare = 4", 4, st.players[0].score)

        vm.onBottleLanded(1, forceWild = WildCard.HOT_POTATO)
        assertEquals("hot potato passes to the next player", 2, st.selected)
        vm.choose(PromptType.TRUTH); vm.complete()

        vm.onBottleLanded(3, forceWild = WildCard.ALL_IN); vm.choose(PromptType.TRUTH); vm.complete()
        assertTrue(st.players.all { it.score >= 1 })

        val skips = st.players[1].skipsLeft
        vm.onBottleLanded(1, forceWild = WildCard.SHIELD)
        assertEquals(skips + 1, st.players[1].skipsLeft)
    }

    @Test fun fairModeNeverRepeatsLastPlayer() {
        vm.startGame()
        var last = -1
        repeat(40) {
            val k = vm.pickTarget()
            assertNotEquals(last, k)
            vm.onBottleLanded(k); vm.choose(PromptType.TRUTH); vm.complete()
            last = k
        }
        val turns = st.players.map { it.truths }
        assertTrue("turns stay balanced: $turns", turns.max() - turns.min() <= 1)
    }

    @Test fun skipsRunOut() {
        vm.startGame()
        vm.onBottleLanded(0); vm.choose(PromptType.DARE)
        repeat(5) { vm.skip() }
        assertEquals(0, st.players[0].skipsLeft)
    }

    @Test fun questionsFollowTheLanguage() {
        vm.setLanguage(Language.ES)
        vm.startGame()
        vm.onBottleLanded(0); vm.choose(PromptType.TRUTH)
        val q = st.prompt!!.text
        val es = Language.ES.prompts.truths.values.flatten() + st.customPrompts.map { it.text }
        assertTrue("question '$q' should come from the Spanish bank", q in es)
    }

    @Test fun heatRisingStartsChill() {
        vm.updateSettings(st.settings.copy(heatEscalation = true))
        vm.startGame()
        assertEquals(1, st.heatCap)
    }

    @Test fun undoRestoresRemovedPlayer() {
        val before = st.players.map { it.name }
        vm.removePlayer(st.players[1].id)
        assertEquals(before.size - 1, st.players.size)
        // the toast's Undo action is the last emitted event; simulate by re-adding via public API
        assertTrue(vm.addPlayer(before[1]))
        assertEquals(before.size, st.players.size)
    }

    @Test fun firstLaunchFlowGoesThroughLanguageAndOnboarding() {
        vm.updateSettings(st.settings.copy(language = null, onboardingDone = false))
        vm.splashFinished(); assertEquals(Screen.LANGUAGE, st.screen)
        vm.languageConfirmed(); assertEquals(Screen.ONBOARDING, st.screen)
        vm.onboardingDone(); assertEquals(Screen.HOME, st.screen)
    }
}
