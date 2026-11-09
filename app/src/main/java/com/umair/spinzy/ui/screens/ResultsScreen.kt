package com.umair.spinzy.ui.screens

import com.umair.spinzy.i18n.LocalStrings
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.umair.spinzy.data.Player
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.game.Screen
import com.umair.spinzy.ui.components.ConfettiRain
import com.umair.spinzy.ui.components.GlassCard
import com.umair.spinzy.ui.components.IconBadge
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.Wine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.DirectionsRun
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.material.icons.rounded.ThumbDown
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.PlayerBubble
import com.umair.spinzy.ui.components.enterUp
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.CoralDeep
import com.umair.spinzy.ui.theme.PlayerColors
import com.umair.spinzy.ui.theme.Sunflower
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextMid
import kotlinx.coroutines.delay
import com.umair.spinzy.audio.LocalSfx
import com.umair.spinzy.audio.Sfx

@Composable
fun ResultsScreen(state: GameState, vm: GameViewModel) {
    val s = LocalStrings.current
    val ranked = state.players.sortedByDescending { it.score }
    val sfx = LocalSfx.current
    LaunchedEffect(Unit) { delay(300); sfx?.play(Sfx.WIN) }
    Box(Modifier.fillMaxSize()) {
        ConfettiRain(Modifier.fillMaxSize())
        Column(
            Modifier.fillMaxSize().safeDrawingPadding().padding(horizontal = 20.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(24.dp))
            IconBadge(Icons.Rounded.EmojiEvents, Modifier.enterUp(0), size = 84.dp, bg = Peach, tint = Wine)
            Spacer(Modifier.height(12.dp))
            Text(s.partyChampion, style = MaterialTheme.typography.labelMedium, color = Sunflower, modifier = Modifier.enterUp(80))
            Text(ranked.firstOrNull()?.name ?: "—", style = MaterialTheme.typography.displayMedium, color = TextHigh, modifier = Modifier.enterUp(140))
            Spacer(Modifier.height(24.dp))

            // Podium: 2nd · 1st · 3rd
            Row(Modifier.fillMaxWidth().height(260.dp), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Bottom) {
                listOf(1, 0, 2).forEach { place ->
                    val p = ranked.getOrNull(place)
                    Box(Modifier.weight(1f)) {
                        if (p != null) Podium(p, place)
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            Awards(state.players, Modifier.enterUp(600))
            Spacer(Modifier.height(24.dp))

            GlowButton(s.playAgain, colors = listOf(Coral, CoralDeep), icon = Icons.Rounded.Replay, modifier = Modifier.fillMaxWidth().enterUp(700)) { vm.startGame() }
            Spacer(Modifier.height(12.dp))
            Text(
                s.backToHome, style = MaterialTheme.typography.labelLarge, color = TextMid,
                modifier = Modifier.pressable { vm.go(Screen.HOME) }.padding(12.dp),
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun Podium(p: Player, place: Int) {
    val s = LocalStrings.current
    val heights = listOf(200f, 150f, 115f)
    val grow = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        delay(250L + (2 - place) * 180L)
        grow.animateTo(1f, spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessLow))
    }
    val color = PlayerColors[p.colorIndex % PlayerColors.size]
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        PlayerBubble(p.avatar, p.name, color, size = if (place == 0) 64.dp else 52.dp, highlighted = place == 0)
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier.fillMaxWidth().height((heights[place] * grow.value.coerceAtLeast(0f)).dp)
                .background(Brush.verticalGradient(listOf(color, color.copy(alpha = 0.25f))), RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 10.dp)) {
                Text(listOf(s.place1, s.place2, s.place3)[place], style = MaterialTheme.typography.labelMedium, color = Wine.copy(alpha = 0.7f))
                Text("${p.score}", style = MaterialTheme.typography.displaySmall, color = Wine)
            }
        }
    }
}

@Composable
private fun Awards(players: List<Player>, modifier: Modifier) {
    val s = LocalStrings.current
    val awards = buildList {
        players.maxByOrNull { it.dares }?.takeIf { it.dares > 0 }?.let { add(Triple(Icons.Rounded.Bolt, s.awardDaredevil, it.name)) }
        players.maxByOrNull { it.truths }?.takeIf { it.truths > 0 }?.let { add(Triple(Icons.AutoMirrored.Rounded.MenuBook, s.awardOpenBook, it.name)) }
        players.maxByOrNull { it.chickens }?.takeIf { it.chickens > 0 }?.let { add(Triple(Icons.AutoMirrored.Rounded.DirectionsRun, s.awardChicken, it.name)) }
        players.maxByOrNull { it.busted }?.takeIf { it.busted > 0 }?.let { add(Triple(Icons.Rounded.ThumbDown, s.awardLiar, it.name)) }
    }
    if (awards.isEmpty()) return
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        awards.forEach { (icon, title, name) ->
            GlassCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    IconBadge(icon, size = 40.dp)
                    Spacer(Modifier.width(12.dp))
                    Text(title, style = MaterialTheme.typography.titleMedium, color = TextMid, modifier = Modifier.weight(1f))
                    Text(name, style = MaterialTheme.typography.titleMedium, color = TextHigh)
                }
            }
        }
    }
}
