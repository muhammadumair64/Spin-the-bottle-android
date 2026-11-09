package com.umair.spinzy.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.i18n.Language
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.ui.components.GlassCard
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.IconBadge
import com.umair.spinzy.ui.components.Squircle
import com.umair.spinzy.ui.components.enterUp
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.CoralDeep
import com.umair.spinzy.ui.theme.GlassStroke
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine

@Composable
fun LanguageScreen(state: GameState, vm: GameViewModel) {
    val s = LocalStrings.current
    val current = state.language
    val spin = rememberInfiniteTransition(label = "globe")
    val globe by spin.animateFloat(-12f, 12f, infiniteRepeatable(tween(2600, easing = LinearEasing), androidx.compose.animation.core.RepeatMode.Reverse), label = "g")

    Column(Modifier.fillMaxSize().safeDrawingPadding().padding(horizontal = 20.dp)) {
        Spacer(Modifier.height(18.dp))
        IconBadge(Icons.Rounded.Language, Modifier.enterUp(0).graphicsLayer { rotationZ = globe }, size = 58.dp, bg = Peach, tint = Wine)
        Spacer(Modifier.height(16.dp))
        // headline cross-fades when the language changes — instant preview
        AnimatedContent(s.chooseLanguage, transitionSpec = { (fadeIn(tween(250)) + slideInVertically { it / 3 }) togetherWith fadeOut(tween(120)) }, label = "title") { t ->
            Text(t, style = MaterialTheme.typography.displaySmall, color = TextHigh)
        }
        AnimatedContent(s.chooseLanguageSub, transitionSpec = { fadeIn(tween(250)) togetherWith fadeOut(tween(120)) }, label = "sub") { t ->
            Text(t, style = MaterialTheme.typography.bodyLarge, color = TextMid)
        }
        Spacer(Modifier.height(18.dp))
        LazyVerticalGrid(
            GridCells.Fixed(2), Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
        ) {
            itemsIndexed(Language.entries) { i, lang ->
                LanguageCard(lang, lang == current, Modifier.enterUp(60 + i * 35)) { vm.setLanguage(lang) }
            }
        }
        GlowButton(
            s.continueLabel, colors = listOf(Coral, CoralDeep), icon = Icons.AutoMirrored.Rounded.ArrowForward,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        ) { vm.languageConfirmed() }
    }
}

@Composable
private fun LanguageCard(lang: Language, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    val border by animateColorAsState(if (selected) Peach else GlassStroke, label = "b")
    val scale by animateFloatAsState(if (selected) 1.03f else 1f, spring(dampingRatio = 0.5f, stiffness = Spring.StiffnessMedium), label = "s")
    GlassCard(
        modifier.fillMaxWidth().height(84.dp).graphicsLayer { scaleX = scale; scaleY = scale }.pressable(onClick = onClick),
        shape = Squircle, borderColor = border, tint = if (selected) Peach else Color.Transparent,
    ) {
        Row(Modifier.fillMaxSize().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(lang.nativeName, style = MaterialTheme.typography.titleLarge, color = TextHigh, maxLines = 1)
                Text(lang.englishName, style = MaterialTheme.typography.labelLarge, color = TextLow, maxLines = 1)
            }
            val check by animateFloatAsState(if (selected) 1f else 0f, spring(dampingRatio = 0.4f), label = "c")
            Box(
                Modifier.size(26.dp).graphicsLayer { scaleX = check; scaleY = check }.clip(CircleShape).background(Peach),
                contentAlignment = Alignment.Center,
            ) { Icon(Icons.Rounded.Check, null, tint = Wine, modifier = Modifier.size(16.dp)) }
        }
    }
}
