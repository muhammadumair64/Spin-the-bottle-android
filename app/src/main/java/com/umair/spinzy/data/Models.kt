package com.umair.spinzy.data

import androidx.compose.ui.graphics.Color

enum class PromptType { TRUTH, DARE }

/** Question packs. Titles/taglines are localized in i18n/Strings. */
enum class Pack(
    val heat: Int,          // 1..5 — shown as a "heat meter"
    val color: Color,
) {
    CHILL(1, Color(0xFFFCD3B6)),
    PARTY(3, Color(0xFFF8B38A)),
    SPICY(5, Color(0xFFF07068)),
    COUPLES(4, Color(0xFFF2A3C7)),
    FAMILY(1, Color(0xFFB67BD0)),
}

data class Prompt(
    val text: String,
    val type: PromptType,
    val pack: Pack?,         // null = custom prompt
)

data class Player(
    val id: Long,
    val name: String,
    val avatar: String,      // vector icon key, see AppIcons
    val colorIndex: Int,
    val score: Int = 0,
    val skipsLeft: Int = 3,
    val truths: Int = 0,
    val dares: Int = 0,
    val chickens: Int = 0,
    val streak: Int = 0,      // completed turns in a row (3 = bonus point)
    val busted: Int = 0,      // truths the group didn't believe
)

/** Keys for the vector avatar icons (mapped in ui/components/AppIcons). */
val AvatarKeys = listOf(
    "star", "heart", "bolt", "fire", "paw", "spa", "diamond", "rocket", "music", "headphones",
    "coffee", "icecream", "cake", "game", "palette", "moon", "tree", "wave", "anchor", "sparkle",
)

data class Settings(
    val haptics: Boolean = true,
    val sound: Boolean = true,
    val fairMode: Boolean = true,
    val dareSeconds: Int = 30,
    val wildCards: Boolean = true,
    val heatEscalation: Boolean = false,
    val shakeToSpin: Boolean = true,
    val skin: BottleSkin = BottleSkin.NEON,
    val coinA: String = "",          // empty = localized "Heads"
    val coinB: String = "",          // empty = localized "Tails"
    val language: String? = null,    // null = not chosen yet (first launch)
    val onboardingDone: Boolean = false,
    val gamesPlayed: Int = 0,
    val rated: Boolean = false,
)

/** Random twists that can hit after the bottle lands. */
enum class WildCard(val color: Color) {
    DOUBLE(Color(0xFFF8B38A)),
    ALL_IN(Color(0xFFF2A3C7)),
    HOT_POTATO(Color(0xFFF59A76)),
    MIRROR(Color(0xFFB67BD0)),
    SHIELD(Color(0xFFFCD3B6)),
}

/** Cosmetic bottle styles. Colors: glass gradient (top→bottom), liquid (top→bottom), cap. */
enum class BottleSkin(
    val glass: List<Color>,
    val liquid: List<Color>,
    val cap: Color,
    val glow: Color,
) {
    NEON(listOf(Color(0x99FCD3B6), Color(0xBFF2A3C7), Color(0xF2F07068)), listOf(Color(0xFFB0305E), Color(0xFF4B0F38)), Color(0xFFF8B38A), Color(0xFFF07068)),
    CHAMPAGNE(listOf(Color(0x9934D399), Color(0xCC065F46), Color(0xF0064E3B)), listOf(Color(0xFFFDE68A), Color(0xFFF59E0B)), Color(0xFFFFD700), Color(0xFFFFC94D)),
    COLA(listOf(Color(0x99E0F2FE), Color(0xAA7DD3FC), Color(0xCC0EA5E9)), listOf(Color(0xFF7C2D12), Color(0xFF3B0A02)), Color(0xFFEF4444), Color(0xFFEF4444)),
    POTION(listOf(Color(0x99C4B5FD), Color(0xCC7C3AED), Color(0xF04C1D95)), listOf(Color(0xFFA3E635), Color(0xFF15803D)), Color(0xFFE879F9), Color(0xFFA3E635)),
}

enum class PopKind { POINTS, BUSTED, CHICKEN }

/** A floating "+3" pill that rises from the center when points are scored (text is localized in UI). */
data class ScorePop(
    val id: Long,
    val kind: PopKind,
    val points: Int,
    val color: Color,
    val streak: Boolean = false,
    val everyone: Boolean = false,
)
