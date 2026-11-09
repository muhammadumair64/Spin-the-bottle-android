package com.umair.spinzy.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.umair.spinzy.data.AvatarKeys
import com.umair.spinzy.data.Pack
import com.umair.spinzy.data.Player
import com.umair.spinzy.data.PopKind
import com.umair.spinzy.data.Prompt
import com.umair.spinzy.data.PromptBank
import com.umair.spinzy.data.PromptType
import com.umair.spinzy.data.SavedPlayer
import com.umair.spinzy.data.ScorePop
import com.umair.spinzy.data.Settings
import com.umair.spinzy.data.SpinzyPrefs
import com.umair.spinzy.data.WildCard
import com.umair.spinzy.i18n.Language
import com.umair.spinzy.ui.theme.Aqua
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.PlayerColors
import com.umair.spinzy.ui.theme.Sunflower
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

enum class Screen { SPLASH, LANGUAGE, ONBOARDING, HOME, SETUP, GAME, RESULTS, CUSTOM, SETTINGS, COIN, RPS, DICE, WHEEL }
enum class Phase { SPIN, CHOOSE, PROMPT }

const val MAX_PLAYERS = 12

data class GameState(
    val screen: Screen = Screen.SPLASH,
    val players: List<Player> = emptyList(),
    val packs: Set<Pack> = setOf(Pack.CHILL, Pack.PARTY),
    val useCustom: Boolean = true,
    val customPrompts: List<Prompt> = emptyList(),
    val settings: Settings = Settings(),
    val phase: Phase = Phase.SPIN,
    val selected: Int? = null,
    val lastSelected: Int? = null,
    val prompt: Prompt? = null,
    val turn: Int = 0,
    val loaded: Boolean = false,
    val wild: WildCard? = null,
    val mirrorWith: Int? = null,
    val pop: ScorePop? = null,
    /** Language / onboarding opened from Settings (so "back" returns there). */
    val fromSettings: Boolean = false,
) {
    val canStart get() = players.size >= 2 && (packs.isNotEmpty() || (useCustom && customPrompts.isNotEmpty()))
    val selectedPlayer get() = selected?.let { players.getOrNull(it) }
    val language: Language get() = Language.fromCode(settings.language) ?: Language.deviceDefault()

    /** Heat Escalation: max pack heat allowed right now (1..5). Rises every ~3 rounds. */
    val heatCap: Int
        get() = if (!settings.heatEscalation) 5
        else (1 + (turn * 4) / (players.size * 3).coerceAtLeast(1)).coerceIn(1, 5)
}

class GameViewModel(app: Application) : AndroidViewModel(app) {

    private val prefs = SpinzyPrefs(app)
    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<UiEvent>(extraBufferCapacity = 16)
    val events: SharedFlow<UiEvent> = _events.asSharedFlow()

    private val usedPrompts = mutableSetOf<String>()
    private var nextId = 1L

    init {
        viewModelScope.launch {
            val saved = prefs.players.first()
            val settings = prefs.settings.first()
            val custom = prefs.customPrompts.first()
            val packs = prefs.packs.first()
            _state.update {
                it.copy(
                    players = saved.map { s -> Player(nextId++, s.name, s.avatar, s.colorIndex) },
                    settings = settings, customPrompts = custom, packs = packs, loaded = true,
                )
            }
        }
    }

    private fun toast(tone: ToastTone, text: (com.umair.spinzy.i18n.Strings) -> String, label: ((com.umair.spinzy.i18n.Strings) -> String)? = null, action: (() -> Unit)? = null) {
        _events.tryEmit(UiEvent.Toast(tone, text, label, action))
    }

    // ---------- Navigation ----------
    fun go(screen: Screen) = _state.update { it.copy(screen = screen) }

    /** Called when the animated splash finishes. */
    fun splashFinished() {
        val s = _state.value.settings
        go(
            when {
                s.language == null -> Screen.LANGUAGE
                !s.onboardingDone -> Screen.ONBOARDING
                else -> Screen.HOME
            }
        )
    }

    fun openLanguageFromSettings() = _state.update { it.copy(screen = Screen.LANGUAGE, fromSettings = true) }
    fun openOnboardingFromSettings() = _state.update { it.copy(screen = Screen.ONBOARDING, fromSettings = true) }

    fun setLanguage(l: Language) {
        val first = _state.value.settings.language == null
        val s = _state.value.settings.copy(language = l.code)
        usedPrompts.clear()
        updateSettings(s)
        if (!first) toast(ToastTone.INFO, { it.languageChanged(l.nativeName) })
    }

    fun languageConfirmed() {
        val st = _state.value
        if (st.settings.language == null) setLanguage(st.language)
        _state.update {
            it.copy(
                screen = when {
                    it.fromSettings -> Screen.SETTINGS
                    !it.settings.onboardingDone -> Screen.ONBOARDING
                    else -> Screen.HOME
                },
                fromSettings = false,
            )
        }
    }

    fun onboardingDone() {
        val from = _state.value.fromSettings
        updateSettings(_state.value.settings.copy(onboardingDone = true))
        _state.update { it.copy(screen = if (from) Screen.SETTINGS else Screen.HOME, fromSettings = false) }
    }

    fun back(): Boolean {
        val s = _state.value
        val target = when (s.screen) {
            Screen.HOME, Screen.SPLASH -> return false
            Screen.LANGUAGE -> if (s.fromSettings) Screen.SETTINGS else return false
            Screen.ONBOARDING -> { onboardingDone(); return true }
            Screen.SETUP, Screen.SETTINGS, Screen.COIN, Screen.RPS, Screen.DICE, Screen.WHEEL -> Screen.HOME
            Screen.CUSTOM -> Screen.SETUP
            Screen.GAME -> Screen.SETUP
            Screen.RESULTS -> Screen.HOME
        }
        _state.update { it.copy(screen = target, fromSettings = false) }
        return true
    }

    // ---------- Players ----------
    /** Returns true if the player was added; otherwise emits validation feedback. */
    fun addPlayer(name: String): Boolean {
        val clean = name.trim().take(14)
        val st = _state.value
        when {
            clean.isEmpty() -> { _events.tryEmit(UiEvent.ShakeInput); toast(ToastTone.WARNING, { it.typeNameFirst }); return false }
            st.players.any { it.name.equals(clean, ignoreCase = true) } -> {
                _events.tryEmit(UiEvent.ShakeInput); toast(ToastTone.WARNING, { it.nameTaken }); return false
            }
            st.players.size >= MAX_PLAYERS -> { toast(ToastTone.WARNING, { it.maxPlayers }); return false }
        }
        _state.update {
            val used = it.players.map { p -> p.avatar }.toSet()
            val avatar = AvatarKeys.firstOrNull { e -> e !in used } ?: AvatarKeys.random()
            val color = it.players.size % PlayerColors.size
            it.copy(players = it.players + Player(nextId++, clean, avatar, color))
        }
        persistPlayers()
        toast(ToastTone.SUCCESS, { it.playerAdded(clean) })
        return true
    }

    fun removePlayer(id: Long) {
        val list = _state.value.players
        val index = list.indexOfFirst { it.id == id }
        if (index < 0) return
        val removed = list[index]
        _state.update { it.copy(players = it.players.filterNot { p -> p.id == id }) }
        persistPlayers()
        toast(ToastTone.INFO, { it.playerRemoved(removed.name) }, { it.undo }) {
            _state.update { s ->
                if (s.players.any { it.id == removed.id }) s
                else s.copy(players = s.players.toMutableList().apply { add(index.coerceAtMost(size), removed) })
            }
            persistPlayers()
        }
    }

    fun cycleEmoji(id: Long) {
        updatePlayer(id) { p ->
            val i = AvatarKeys.indexOf(p.avatar)
            p.copy(avatar = AvatarKeys[(i + 1).mod(AvatarKeys.size)])
        }
        persistPlayers()
    }

    fun cycleColor(id: Long) {
        updatePlayer(id) { p -> p.copy(colorIndex = (p.colorIndex + 1) % PlayerColors.size) }
        persistPlayers()
    }

    private fun updatePlayer(id: Long, f: (Player) -> Player) =
        _state.update { it.copy(players = it.players.map { p -> if (p.id == id) f(p) else p }) }

    private fun persistPlayers() = viewModelScope.launch {
        prefs.savePlayers(_state.value.players.map { SavedPlayer(it.name, it.avatar, it.colorIndex) })
    }

    // ---------- Packs & custom prompts ----------
    fun togglePack(pack: Pack) {
        _state.update { it.copy(packs = if (pack in it.packs) it.packs - pack else it.packs + pack) }
        viewModelScope.launch { prefs.savePacks(_state.value.packs) }
    }

    fun toggleCustom() = _state.update { it.copy(useCustom = !it.useCustom) }

    fun addCustom(text: String, type: PromptType): Boolean {
        val t = text.trim()
        if (t.isEmpty()) { _events.tryEmit(UiEvent.ShakeInput); return false }
        _state.update { it.copy(customPrompts = listOf(Prompt(t, type, null)) + it.customPrompts) }
        viewModelScope.launch { prefs.saveCustomPrompts(_state.value.customPrompts) }
        toast(ToastTone.SUCCESS, { it.questionAdded })
        return true
    }

    fun removeCustom(prompt: Prompt) {
        val index = _state.value.customPrompts.indexOf(prompt)
        _state.update { it.copy(customPrompts = it.customPrompts - prompt) }
        viewModelScope.launch { prefs.saveCustomPrompts(_state.value.customPrompts) }
        toast(ToastTone.INFO, { it.questionDeleted }, { it.undo }) {
            _state.update { s -> s.copy(customPrompts = s.customPrompts.toMutableList().apply { add(index.coerceIn(0, size), prompt) }) }
            viewModelScope.launch { prefs.saveCustomPrompts(_state.value.customPrompts) }
        }
    }

    // ---------- Settings ----------
    fun updateSettings(s: Settings) {
        _state.update { it.copy(settings = s) }
        viewModelScope.launch { prefs.saveSettings(s) }
    }

    /** Toggle with a confirmation toast. */
    fun toggleSetting(name: (com.umair.spinzy.i18n.Strings) -> String, newValue: Boolean, apply: (Settings) -> Settings) {
        updateSettings(apply(_state.value.settings))
        toast(ToastTone.INFO, { s -> if (newValue) s.settingOn(name(s)) else s.settingOff(name(s)) })
    }

    fun markRated() = updateSettings(_state.value.settings.copy(rated = true))

    // ---------- Game loop ----------
    fun startGame() {
        if (!_state.value.canStart) return
        usedPrompts.clear()
        _state.update {
            it.copy(
                screen = Screen.GAME, phase = Phase.SPIN, selected = null, lastSelected = null, prompt = null, turn = 0,
                wild = null, mirrorWith = null, pop = null,
                players = it.players.map { p -> p.copy(score = 0, skipsLeft = 3, truths = 0, dares = 0, chickens = 0, streak = 0, busted = 0) },
            )
        }
    }

    /** Decides who the bottle will land on. Fair mode: nobody twice in a row, fewest turns first. */
    fun pickTarget(): Int {
        val s = _state.value
        val n = s.players.size
        if (n <= 1) return 0
        val candidates = if (s.settings.fairMode) {
            val turns = s.players.map { it.truths + it.dares + it.chickens }
            val pool = (0 until n).filter { it != s.lastSelected }
            val min = pool.minOf { turns[it] }
            pool.filter { turns[it] == min }
        } else (0 until n).toList()
        return candidates[Random.nextInt(candidates.size)]
    }

    /** Bottle stopped on [index]. May trigger a Wild Card twist. */
    fun onBottleLanded(index: Int, forceWild: WildCard? = null) {
        val s = _state.value
        val n = s.players.size
        val wild = forceWild ?: if (s.settings.wildCards && s.turn > 0 && Random.nextFloat() < 0.22f) {
            WildCard.entries.filter { c ->
                when (c) {
                    WildCard.MIRROR -> s.lastSelected != null && s.lastSelected != index
                    WildCard.ALL_IN -> n >= 3
                    else -> true
                }
            }.randomOrNull()
        } else null
        val selected = if (wild == WildCard.HOT_POTATO) (index + 1) % n else index
        _state.update {
            it.copy(
                phase = Phase.CHOOSE, selected = selected, lastSelected = selected, wild = wild,
                mirrorWith = if (wild == WildCard.MIRROR) s.lastSelected else null,
                players = if (wild == WildCard.SHIELD) it.players.mapIndexed { i, p -> if (i == selected) p.copy(skipsLeft = p.skipsLeft + 1) else p } else it.players,
            )
        }
    }

    fun choose(type: PromptType?) {
        val t = type ?: if (Random.nextBoolean()) PromptType.TRUTH else PromptType.DARE
        _state.update { it.copy(phase = Phase.PROMPT, prompt = drawPrompt(t)) }
    }

    private fun drawPrompt(type: PromptType): Prompt {
        val s = _state.value
        val cap = s.heatCap
        val packs = s.packs.filter { it.heat <= cap }.toSet()
            .ifEmpty { s.packs.minByOrNull { it.heat }?.let { setOf(it) } ?: emptySet() }
        val all = (PromptBank.prompts(packs, s.language) + if (s.useCustom) s.customPrompts else emptyList())
            .filter { it.type == type }
            .ifEmpty { PromptBank.prompts(setOf(Pack.CHILL), s.language).filter { it.type == type } }
        var fresh = all.filter { it.text !in usedPrompts && it.text != s.prompt?.text }
        if (fresh.isEmpty()) {
            usedPrompts.removeAll(all.map { it.text }.toSet())
            fresh = all.filter { it.text != s.prompt?.text }.ifEmpty { all }
        }
        return fresh.random().also { usedPrompts += it.text }
    }

    /** Turn completed (dare done / truth believed). Handles Double, All In, Mirror and streak bonus. */
    fun complete() {
        val s = _state.value
        val idx = s.selected ?: return
        val truth = s.prompt?.type == PromptType.TRUTH
        val base = (if (truth) 1 else 2) * (if (s.wild == WildCard.DOUBLE) 2 else 1)
        val streak = s.players[idx].streak + 1
        val bonus = if (streak % 3 == 0) 1 else 0
        val players = s.players.mapIndexed { i, p ->
            when {
                i == idx -> p.copy(
                    score = p.score + base + bonus, streak = streak,
                    truths = p.truths + if (truth) 1 else 0, dares = p.dares + if (truth) 0 else 1,
                )
                s.wild == WildCard.ALL_IN -> p.copy(score = p.score + base)
                s.wild == WildCard.MIRROR && i == s.mirrorWith -> p.copy(score = p.score + base)
                else -> p
            }
        }
        endTurn(players, ScorePop(System.nanoTime(), PopKind.POINTS, base + bonus, if (truth) Aqua else Coral, streak = bonus > 0, everyone = s.wild == WildCard.ALL_IN))
        if (bonus > 0) { val n = s.players[idx].name; toast(ToastTone.FIRE, { it.streakToast(n) }) }
    }

    /** Group Verdict: the group didn't believe the truth. No points, streak broken. */
    fun busted() {
        val s = _state.value
        val idx = s.selected ?: return
        val players = s.players.mapIndexed { i, p ->
            if (i == idx) p.copy(truths = p.truths + 1, busted = p.busted + 1, streak = 0) else p
        }
        endTurn(players, ScorePop(System.nanoTime(), PopKind.BUSTED, 0, Sunflower))
    }

    fun chickenOut() {
        val s = _state.value
        val idx = s.selected ?: return
        val players = s.players.mapIndexed { i, p ->
            if (i == idx) p.copy(score = p.score - 1, chickens = p.chickens + 1, streak = 0) else p
        }
        endTurn(players, ScorePop(System.nanoTime(), PopKind.CHICKEN, -1, Coral))
    }

    fun skip() {
        val s = _state.value
        val idx = s.selected ?: return
        val player = s.players[idx]
        val type = s.prompt?.type ?: return
        if (player.skipsLeft <= 0) { toast(ToastTone.WARNING, { it.noSkipsLeft }); return }
        _state.update {
            it.copy(
                players = it.players.mapIndexed { i, p -> if (i == idx) p.copy(skipsLeft = p.skipsLeft - 1) else p },
                prompt = drawPrompt(type),
            )
        }
    }

    private fun endTurn(players: List<Player>, pop: ScorePop) = _state.update {
        it.copy(
            players = players, phase = Phase.SPIN, prompt = null, selected = null,
            wild = null, mirrorWith = null, turn = it.turn + 1, pop = pop,
        )
    }

    fun endGame() {
        val s = _state.value
        val games = s.settings.gamesPlayed + 1
        updateSettings(s.settings.copy(gamesPlayed = games))
        _state.update { it.copy(screen = Screen.RESULTS, phase = Phase.SPIN, selected = null, prompt = null, wild = null, pop = null) }
        if (!s.settings.rated && games in setOf(2, 5, 10)) _events.tryEmit(UiEvent.AskRating)
    }
}
