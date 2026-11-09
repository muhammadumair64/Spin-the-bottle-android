package com.umair.spinzy.ui.screens

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.unit.em
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Casino
import androidx.compose.material.icons.rounded.ContentCut
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Toll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.umair.spinzy.audio.LocalSfx
import com.umair.spinzy.audio.Sfx
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.game.Screen
import com.umair.spinzy.ui.components.GlassIconButton
import com.umair.spinzy.ui.components.IconBadge
import com.umair.spinzy.ui.components.NeonBottle
import com.umair.spinzy.ui.components.Squircle
import com.umair.spinzy.ui.components.enterUp
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.theme.Apricot
import com.umair.spinzy.ui.theme.Berry
import com.umair.spinzy.ui.theme.Blush
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.Lilac
import com.umair.spinzy.ui.theme.Mauve
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.Raspberry
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine
import kotlinx.coroutines.launch

/** Party Hub — every game lives here. */
@Composable
fun HomeScreen(state: GameState, vm: GameViewModel) {
    val s = LocalStrings.current
    val coinA = state.settings.coinA.ifBlank { s.heads }
    val coinB = state.settings.coinB.ifBlank { s.tails }
    val t = rememberInfiniteTransition(label = "hub")
    val bob by t.animateFloat(-1f, 1f, infiniteRepeatable(tween(3200), RepeatMode.Reverse), label = "bob")

    // Everything fits on one screen: the tiles share the free height by weight (no scrolling).
    Column(Modifier.fillMaxSize().safeDrawingPadding().padding(horizontal = 20.dp).padding(bottom = 14.dp)) {
        Row(Modifier.fillMaxWidth().padding(top = 6.dp).enterUp(0), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(s.partyGames, style = MaterialTheme.typography.labelMedium, color = Peach)
                Text(s.appName, style = MaterialTheme.typography.displayMedium, color = TextHigh, maxLines = 1)
            }
            GlassIconButton(onClick = { vm.go(Screen.SETTINGS) }) {
                Icon(Icons.Rounded.Settings, contentDescription = s.settings, tint = TextHigh)
            }
        }
        Row(Modifier.enterUp(60)) {
            Text(s.taglineA, style = MaterialTheme.typography.titleMedium, color = TextMid, maxLines = 1)
            Text(s.taglineB, style = MaterialTheme.typography.titleMedium.copy(fontStyle = FontStyle.Italic), color = Peach, maxLines = 1)
        }
        Spacer(Modifier.height(14.dp))

        HeroTile(state, vm, Modifier.weight(1.3f).enterUp(120).graphicsLayer { translationY = bob * 5f })
        Spacer(Modifier.height(12.dp))
        WheelTile(s.spinWheel, s.spinWheelSub, Modifier.weight(0.6f).enterUp(170).graphicsLayer { translationY = -bob * 3f }) { vm.go(Screen.WHEEL) }
        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth().weight(1f).enterUp(220), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GameTile(
                s.coinFlip, s.coinSubtitle(coinA, coinB), Icons.Rounded.Toll,
                listOf(PeachLight, Apricot), Modifier.weight(1f).graphicsLayer { rotationZ = -2f; translationY = -bob * 3f },
            ) { vm.go(Screen.COIN) }
            GameTile(
                s.rps, s.rpsSubtitle, Icons.Rounded.ContentCut,
                listOf(Blush, Mauve), Modifier.weight(1f).graphicsLayer { rotationZ = 2f; translationY = bob * 3f },
            ) { vm.go(Screen.RPS) }
        }
        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth().weight(1f).enterUp(270), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GameTile(
                s.dice, s.diceSubtitle, Icons.Rounded.Casino,
                listOf(Lilac, Color(0xFF8E4FA8)), Modifier.weight(1f).graphicsLayer { rotationZ = 2f; translationY = bob * 2f },
            ) { vm.go(Screen.DICE) }
            GameTile(
                s.myQuestions, s.savedCount(state.customPrompts.size), Icons.Rounded.EditNote,
                listOf(Berry, Wine), Modifier.weight(1f).graphicsLayer { rotationZ = -2f; translationY = -bob * 2f },
                textColor = TextHigh, badgeBg = Raspberry,
            ) { vm.go(Screen.CUSTOM) }
        }
    }
}

/** Big Truth-or-Dare tile with a live, tappable bottle. */
@Composable
private fun HeroTile(state: GameState, vm: GameViewModel, modifier: Modifier) {
    val s = LocalStrings.current
    val spin = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val sfx = LocalSfx.current
    val count = state.players.size
    Box(
        modifier.fillMaxWidth().fillMaxHeight()
            .graphicsLayer { rotationZ = -1.5f; shadowElevation = 40f; shape = Squircle; clip = true; ambientShadowColor = Coral; spotShadowColor = Coral }
            .background(Brush.linearGradient(listOf(Peach, Coral, Raspberry)))
            .pressable(pressedScale = 0.97f) { vm.go(Screen.SETUP) },
    ) {
        // bottle on the right — tap it for a free spin
        NeonBottle(
            Modifier.align(Alignment.CenterEnd).padding(end = 30.dp).fillMaxHeight(0.72f).aspectRatio(0.35f)
                .graphicsLayer { rotationZ = 24f + spin.value }
                .pressable(pressedScale = 0.9f) {
                    scope.launch {
                        sfx?.play(Sfx.WHOOSH, 0.7f)
                        spin.animateTo(spin.value + 1080f, tween(1900, easing = CubicBezierEasing(0.12f, 0.8f, 0.2f, 1f)))
                        sfx?.play(Sfx.LAND, 0.6f)
                    }
                },
            skin = state.settings.skin,
        )
        Column(
            Modifier.align(Alignment.CenterStart).fillMaxWidth(0.66f).fillMaxHeight().padding(start = 24.dp, top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(s.spinTheBottleCaps, style = MaterialTheme.typography.labelMedium, color = Wine.copy(alpha = 0.7f))
            Spacer(Modifier.height(4.dp))
            // Auto-sizes so long titles (e.g. Russian, Urdu) always fit next to the bottle
            BasicText(
                s.truthOrDareTwoLines, style = MaterialTheme.typography.displayMedium.copy(color = Wine, lineHeight = 1.08.em), maxLines = 2,
                autoSize = TextAutoSize.StepBased(minFontSize = 18.sp, maxFontSize = 40.sp),
                modifier = Modifier.weight(1f, fill = false),   // bounded height → shrinks on short screens too
            )
            Spacer(Modifier.height(10.dp))
            Row(
                Modifier.clip(Squircle).background(Wine).padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(if (count >= 2) s.playWith(count) else s.start, style = MaterialTheme.typography.labelLarge, color = PeachLight)
                Spacer(Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Rounded.ArrowForward, null, tint = PeachLight, modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Composable
private fun GameTile(
    title: String,
    subtitle: String,
    icon: ImageVector,
    colors: List<Color>,
    modifier: Modifier,
    textColor: Color = Wine,
    badgeBg: Color = Color(0xFF6A1645),
    onClick: () -> Unit,
) {
    BoxWithConstraints(
        modifier.fillMaxHeight()
            .graphicsLayer { shadowElevation = 24f; shape = Squircle; clip = true; ambientShadowColor = colors[0]; spotShadowColor = colors[0] }
            .background(Brush.linearGradient(colors))
            .pressable(pressedScale = 0.93f, onClick = onClick),
    ) {
        val compact = maxHeight < 140.dp            // short phones: smaller badge, no subtitle
        Column(Modifier.fillMaxSize().padding(if (compact) 12.dp else 16.dp)) {
            IconBadge(icon, size = if (compact) 32.dp else 38.dp, bg = badgeBg)
            Spacer(Modifier.weight(1f))
            BasicText(
                title, style = MaterialTheme.typography.titleLarge.copy(color = textColor, lineHeight = 1.1.em), maxLines = 2,
                autoSize = TextAutoSize.StepBased(minFontSize = 13.sp, maxFontSize = 20.sp),
            )
            if (!compact) Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = textColor.copy(alpha = 0.72f), maxLines = 1, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis)
        }
    }
}

/** Wide, colourful tile with a slowly turning mini wheel. */
@Composable
private fun WheelTile(title: String, subtitle: String, modifier: Modifier, onClick: () -> Unit) {
    val t = rememberInfiniteTransition(label = "wheelTile")
    val rot by t.animateFloat(0f, 360f, infiniteRepeatable(tween(16_000, easing = androidx.compose.animation.core.LinearEasing)), label = "rot")
    Row(
        modifier.fillMaxWidth().fillMaxHeight()
            .graphicsLayer { rotationZ = 1f; shadowElevation = 28f; shape = Squircle; clip = true; ambientShadowColor = Lilac; spotShadowColor = Lilac }
            .background(Brush.linearGradient(listOf(Color(0xFF7DB9F2), Lilac, Blush)))
            .pressable(pressedScale = 0.96f, onClick = onClick)
            .padding(horizontal = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.headlineMedium, color = Wine, maxLines = 1, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = Wine.copy(alpha = 0.75f), maxLines = 1)
        }
        androidx.compose.foundation.Canvas(Modifier.fillMaxHeight(0.74f).aspectRatio(1f).graphicsLayer { rotationZ = rot }) {
            val colors = com.umair.spinzy.wheel.WheelColors
            val n = 8
            for (i in 0 until n) drawArc(colors[i % colors.size], i * 45f, 45f, true)
            drawCircle(Wine, size.minDimension * 0.5f, style = androidx.compose.ui.graphics.drawscope.Stroke(6f))
            drawCircle(PeachLight, size.minDimension * 0.12f)
            drawCircle(Wine, size.minDimension * 0.12f, style = androidx.compose.ui.graphics.drawscope.Stroke(4f))
        }
    }
}
