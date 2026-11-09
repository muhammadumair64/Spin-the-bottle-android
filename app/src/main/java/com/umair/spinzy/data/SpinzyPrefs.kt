package com.umair.spinzy.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "spinzy")

/** Persisted wheel. */
data class WheelSnapshot(
    val items: List<Triple<String, Int, Int>>,
    val picked: List<Triple<String, Int, Int>>,
    val removeWinner: Boolean,
    val customOdds: Boolean,
    val initialized: Boolean = true,
)

/** Saved player roster (without scores). */
data class SavedPlayer(val name: String, val avatar: String, val colorIndex: Int)

/** Everything Spinzy remembers between launches. */
class SpinzyPrefs(private val context: Context) {

    private object Keys {
        val haptics = booleanPreferencesKey("haptics")
        val fairMode = booleanPreferencesKey("fair_mode")
        val dareSeconds = intPreferencesKey("dare_seconds")
        val sound = booleanPreferencesKey("sound")
        val wild = booleanPreferencesKey("wild_cards")
        val heat = booleanPreferencesKey("heat_escalation")
        val shake = booleanPreferencesKey("shake_to_spin")
        val skin = stringPreferencesKey("bottle_skin")
        val coinA = stringPreferencesKey("coin_a")
        val coinB = stringPreferencesKey("coin_b")
        val language = stringPreferencesKey("language")
        val onboarding = booleanPreferencesKey("onboarding_done")
        val games = intPreferencesKey("games_played")
        val rated = booleanPreferencesKey("rated")
        val players = stringPreferencesKey("players")
        val custom = stringPreferencesKey("custom_prompts")
        val packs = stringPreferencesKey("packs")
        val wheelItems = stringPreferencesKey("wheel_items")
        val wheelPicked = stringPreferencesKey("wheel_picked")
        val wheelRemove = booleanPreferencesKey("wheel_remove_winner")
        val wheelCustom = booleanPreferencesKey("wheel_custom_odds")
    }

    private companion object {
        const val FIELD = '\u001F'
        const val RECORD = '\u001E'
    }

    val settings: Flow<Settings> = context.dataStore.data.map { p ->
        Settings(
            haptics = p[Keys.haptics] ?: true,
            fairMode = p[Keys.fairMode] ?: true,
            dareSeconds = p[Keys.dareSeconds] ?: 30,
            sound = p[Keys.sound] ?: true,
            wildCards = p[Keys.wild] ?: true,
            heatEscalation = p[Keys.heat] ?: false,
            shakeToSpin = p[Keys.shake] ?: true,
            skin = BottleSkin.entries.firstOrNull { it.name == p[Keys.skin] } ?: BottleSkin.NEON,
            coinA = p[Keys.coinA] ?: "",
            coinB = p[Keys.coinB] ?: "",
            language = p[Keys.language],
            onboardingDone = p[Keys.onboarding] ?: false,
            gamesPlayed = p[Keys.games] ?: 0,
            rated = p[Keys.rated] ?: false,
        )
    }

    val players: Flow<List<SavedPlayer>> = context.dataStore.data.map { p ->
        p[Keys.players].orEmpty().split(RECORD).mapNotNull { rec ->
            val f = rec.split(FIELD)
            if (f.size == 3) SavedPlayer(f[0], f[1], f[2].toIntOrNull() ?: 0) else null
        }
    }

    val customPrompts: Flow<List<Prompt>> = context.dataStore.data.map { p ->
        p[Keys.custom].orEmpty().split(RECORD).mapNotNull { rec ->
            val f = rec.split(FIELD)
            if (f.size == 2 && f[1].isNotBlank()) {
                Prompt(f[1], if (f[0] == "D") PromptType.DARE else PromptType.TRUTH, null)
            } else null
        }
    }

    val packs: Flow<Set<Pack>> = context.dataStore.data.map { p ->
        val raw = p[Keys.packs]
        if (raw == null) setOf(Pack.CHILL, Pack.PARTY)
        else raw.split(",").mapNotNull { n -> Pack.entries.firstOrNull { it.name == n } }.toSet()
    }

    suspend fun saveSettings(s: Settings) = context.dataStore.edit {
        it[Keys.haptics] = s.haptics
        it[Keys.fairMode] = s.fairMode
        it[Keys.dareSeconds] = s.dareSeconds
        it[Keys.sound] = s.sound
        it[Keys.wild] = s.wildCards
        it[Keys.heat] = s.heatEscalation
        it[Keys.shake] = s.shakeToSpin
        it[Keys.skin] = s.skin.name
        it[Keys.coinA] = s.coinA
        it[Keys.coinB] = s.coinB
        s.language?.let { l -> it[Keys.language] = l }
        it[Keys.onboarding] = s.onboardingDone
        it[Keys.games] = s.gamesPlayed
        it[Keys.rated] = s.rated
    }

    suspend fun savePlayers(list: List<SavedPlayer>) = context.dataStore.edit {
        it[Keys.players] = list.joinToString(RECORD.toString()) { p ->
            listOf(p.name.clean(), p.avatar, p.colorIndex.toString()).joinToString(FIELD.toString())
        }
    }

    suspend fun saveCustomPrompts(list: List<Prompt>) = context.dataStore.edit {
        it[Keys.custom] = list.joinToString(RECORD.toString()) { p ->
            (if (p.type == PromptType.DARE) "D" else "T") + FIELD + p.text.clean()
        }
    }

    suspend fun savePacks(set: Set<Pack>) = context.dataStore.edit {
        it[Keys.packs] = set.joinToString(",") { p -> p.name }
    }

    // ── Spin the Wheel ──
    /** (label, weight, colorIndex) triples. */
    private fun decodeWheel(raw: String?): List<Triple<String, Int, Int>> =
        raw.orEmpty().split(RECORD).mapNotNull { rec ->
            val f = rec.split(FIELD)
            if (f.size == 3 && f[0].isNotBlank()) Triple(f[0], f[1].toIntOrNull() ?: 10, f[2].toIntOrNull() ?: 0) else null
        }

    private fun encodeWheel(list: List<Triple<String, Int, Int>>) =
        list.joinToString(RECORD.toString()) { (l, w, c) -> listOf(l.clean(), w.toString(), c.toString()).joinToString(FIELD.toString()) }

    val wheel: Flow<WheelSnapshot> = context.dataStore.data.map { p ->
        WheelSnapshot(
            items = decodeWheel(p[Keys.wheelItems]),
            picked = decodeWheel(p[Keys.wheelPicked]),
            removeWinner = p[Keys.wheelRemove] ?: false,
            customOdds = p[Keys.wheelCustom] ?: false,
            initialized = p[Keys.wheelItems] != null,
        )
    }

    suspend fun saveWheel(w: WheelSnapshot) = context.dataStore.edit {
        it[Keys.wheelItems] = encodeWheel(w.items)
        it[Keys.wheelPicked] = encodeWheel(w.picked)
        it[Keys.wheelRemove] = w.removeWinner
        it[Keys.wheelCustom] = w.customOdds
    }

    private fun String.clean() = replace(FIELD, ' ').replace(RECORD, ' ').trim()
}
