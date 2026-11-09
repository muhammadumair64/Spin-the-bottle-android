package com.umair.spinzy.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AcUnit
import androidx.compose.material.icons.rounded.Anchor
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Cake
import androidx.compose.material.icons.rounded.Celebration
import androidx.compose.material.icons.rounded.Diamond
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Flip
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Headphones
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Icecream
import androidx.compose.material.icons.rounded.LocalCafe
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.NightsStay
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.Park
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.RocketLaunch
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.Spa
import androidx.compose.material.icons.rounded.SportsEsports
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.SwapHoriz
import androidx.compose.material.icons.rounded.Waves
import androidx.compose.material.icons.rounded.Whatshot
import androidx.compose.ui.graphics.vector.ImageVector
import com.umair.spinzy.data.AvatarKeys
import com.umair.spinzy.data.Pack
import com.umair.spinzy.data.WildCard

/** Every icon in the app is a vector — no emojis anywhere. */
object AppIcons {
    private val avatars: Map<String, ImageVector> = mapOf(
        "star" to Icons.Rounded.Star,
        "heart" to Icons.Rounded.Favorite,
        "bolt" to Icons.Rounded.Bolt,
        "fire" to Icons.Rounded.LocalFireDepartment,
        "paw" to Icons.Rounded.Pets,
        "spa" to Icons.Rounded.Spa,
        "diamond" to Icons.Rounded.Diamond,
        "rocket" to Icons.Rounded.RocketLaunch,
        "music" to Icons.Rounded.MusicNote,
        "headphones" to Icons.Rounded.Headphones,
        "coffee" to Icons.Rounded.LocalCafe,
        "icecream" to Icons.Rounded.Icecream,
        "cake" to Icons.Rounded.Cake,
        "game" to Icons.Rounded.SportsEsports,
        "palette" to Icons.Rounded.Palette,
        "moon" to Icons.Rounded.NightsStay,
        "tree" to Icons.Rounded.Park,
        "wave" to Icons.Rounded.Waves,
        "anchor" to Icons.Rounded.Anchor,
        "sparkle" to Icons.Rounded.AutoAwesome,
    )

    fun avatar(key: String): ImageVector =
        avatars[key] ?: avatars.getValue(AvatarKeys[(key.hashCode() and 0x7fffffff) % AvatarKeys.size])

    fun pack(p: Pack): ImageVector = when (p) {
        Pack.CHILL -> Icons.Rounded.AcUnit
        Pack.PARTY -> Icons.Rounded.Celebration
        Pack.SPICY -> Icons.Rounded.Whatshot
        Pack.COUPLES -> Icons.Rounded.Favorite
        Pack.FAMILY -> Icons.Rounded.Home
    }

    fun wild(w: WildCard): ImageVector = when (w) {
        WildCard.DOUBLE -> Icons.Rounded.Bolt
        WildCard.ALL_IN -> Icons.Rounded.Groups
        WildCard.HOT_POTATO -> Icons.Rounded.SwapHoriz
        WildCard.MIRROR -> Icons.Rounded.Flip
        WildCard.SHIELD -> Icons.Rounded.Shield
    }
}
