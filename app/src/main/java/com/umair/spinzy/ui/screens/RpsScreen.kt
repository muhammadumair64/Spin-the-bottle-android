package com.umair.spinzy.ui.screens

import com.umair.spinzy.i18n.LocalStrings
import android.view.HapticFeedbackConstants
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.umair.spinzy.audio.LocalSfx
import com.umair.spinzy.audio.Sfx
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.ui.components.ConfettiRain
import com.umair.spinzy.ui.components.GlassCard
import com.umair.spinzy.ui.components.SpinzyDialog
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.Rps
import com.umair.spinzy.ui.components.RpsGlyph
import com.umair.spinzy.ui.components.SpinzyTopBar
import com.umair.spinzy.ui.components.Squircle
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.theme.Blush
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.CoralDeep
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.Mauve
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.Apricot
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private enum class RpsStage { PICK_1, PASS, PICK_2, SHAKING, REVEAL }
private const val WIN_TARGET = 3

@Composable
fun RpsScreen(state: GameState, vm: GameViewModel) {
    val s = LocalStrings.current
    var twoPlayer by remember { mutableStateOf(false) }
    var confirmReset by remember { mutableStateOf(false) }
    val names = if (twoPlayer) {
        listOf(state.players.getOrNull(0)?.name ?: s.player1, state.players.getOrNull(1)?.name ?: s.player2)
    } else listOf(s.you, s.phone)
    var stage by remember { mutableStateOf(RpsStage.PICK_1) }
    var pick1 by remember { mutableStateOf<Rps?>(null) }
    var pick2 by remember { mutableStateOf<Rps?>(null) }
    var score1 by remember { mutableIntStateOf(0) }
    var score2 by remember { mutableIntStateOf(0) }
    var beat by remember { mutableStateOf("") }
    val shake = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val sfx = LocalSfx.current
    val view = LocalView.current
    val matchOver = score1 >= WIN_TARGET || score2 >= WIN_TARGET

    fun reset() { stage = RpsStage.PICK_1; pick1 = null; pick2 = null; score1 = 0; score2 = 0; beat = "" }

    fun shoot() {
        stage = RpsStage.SHAKING
        scope.launch {
            for (w in listOf(s.rock, s.paper, s.scissors)) {
                beat = w
                sfx?.play(Sfx.TICK, 0.9f, 0.8f)
                shake.animateTo(1f, tween(170)); shake.animateTo(0f, tween(170))
            }
            beat = s.shoot
            sfx?.play(Sfx.LAND)
            if (state.settings.haptics) view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            val a = pick1!!; val b = pick2!!
            when {
                a.beats(b) -> score1++
                b.beats(a) -> score2++
            }
            stage = RpsStage.REVEAL
            delay(250)
            sfx?.play(if (a == b) Sfx.POP else Sfx.REVEAL)
            if (score1 >= WIN_TARGET || score2 >= WIN_TARGET) { delay(300); sfx?.play(Sfx.WIN) }
        }
    }

    fun choose(c: Rps) {
        when (stage) {
            RpsStage.PICK_1 -> {
                pick1 = c
                if (twoPlayer) stage = RpsStage.PASS else { pick2 = Rps.entries.random(); shoot() }
            }
            RpsStage.PICK_2 -> { pick2 = c; shoot() }
            else -> Unit
        }
    }

    fun next() { pick1 = null; pick2 = null; beat = ""; stage = RpsStage.PICK_1 }

    Box(Modifier.fillMaxSize()) {
        if (matchOver && stage == RpsStage.REVEAL) ConfettiRain(Modifier.fillMaxSize(), count = 90)
        Column(Modifier.fillMaxSize().safeDrawingPadding().padding(horizontal = 20.dp)) {
            SpinzyTopBar(s.rps, s.firstTo(WIN_TARGET), onBack = { vm.back() }) {
                Box(Modifier.size(48.dp).pressable { if (score1 + score2 > 0 && !matchOver) confirmReset = true else reset() }, contentAlignment = Alignment.Center) {
                    Icon(Icons.Rounded.Refresh, s.reset, tint = TextMid)
                }
            }
            // mode switch
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(50)).background(Cream.copy(alpha = 0.07f)).padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                ModeChip(s.vsPhone, Icons.Rounded.PhoneAndroid, !twoPlayer, Modifier.weight(1f)) { twoPlayer = false; reset() }
                ModeChip(s.twoPlayers, Icons.Rounded.Groups, twoPlayer, Modifier.weight(1f)) { twoPlayer = true; reset() }
            }
            Spacer(Modifier.height(16.dp))
            Scoreboard(names, score1, score2)

            // ---------- arena ----------
            Column(Modifier.fillMaxWidth().weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                val revealed = stage == RpsStage.REVEAL
                // opponent (upside-down, like sitting across the table)
                Hand(if (revealed) pick2 else null, listOf(Blush, Mauve), Modifier.graphicsLayer {
                    translationY = shake.value * 40f
                }, upsideDown = true)
                Spacer(Modifier.height(14.dp))
                AnimatedContent(
                    targetState = when {
                        revealed -> outcome(pick1, pick2, names, twoPlayer, s)
                        stage == RpsStage.SHAKING -> beat
                        stage == RpsStage.PASS -> ""
                        stage == RpsStage.PICK_2 -> s.pickInSecret(names[1])
                        else -> if (twoPlayer) s.pickInSecret(names[0]) else s.pickYourMove
                    },
                    transitionSpec = { (fadeIn(tween(150)) + scaleIn(spring(dampingRatio = 0.5f), initialScale = 0.7f)) togetherWith fadeOut(tween(100)) },
                    label = "beat",
                    modifier = Modifier.fillMaxWidth().height(44.dp),
                    contentAlignment = Alignment.Center,
                ) { txt ->
                    Text(txt, style = MaterialTheme.typography.headlineMedium, color = if (txt == s.shoot) Peach else TextHigh, textAlign = TextAlign.Center)
                }
                Spacer(Modifier.height(14.dp))
                Hand(if (revealed) pick1 else null, listOf(PeachLight, Apricot), Modifier.graphicsLayer { translationY = -shake.value * 40f })
            }

            // ---------- controls ----------
            when {
                stage == RpsStage.REVEAL -> GlowButton(
                    if (matchOver) s.newMatch else s.nextRound, colors = listOf(Coral, CoralDeep),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                ) { if (matchOver) reset() else next() }
                stage == RpsStage.PICK_1 || stage == RpsStage.PICK_2 -> Row(
                    Modifier.fillMaxWidth().padding(bottom = 12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) { Rps.entries.forEach { c -> ChoiceButton(c, Modifier.weight(1f)) { choose(c) } } }
                else -> Spacer(Modifier.height(122.dp))
            }
        }

        SpinzyDialog(
            visible = confirmReset, icon = Icons.Rounded.Refresh, title = s.resetMatchTitle, body = s.resetMatchBody,
            confirm = s.newMatch, dismiss = s.cancel, onConfirm = { confirmReset = false; reset() }, onDismiss = { confirmReset = false },
        )
        // ---------- pass-the-phone cover ----------
        AnimatedVisibility(stage == RpsStage.PASS, enter = fadeIn() + slideInVertically { it / 4 }, exit = fadeOut()) {
            Box(Modifier.fillMaxSize().background(Wine.copy(alpha = 0.97f)).pressable(haptic = true) { stage = RpsStage.PICK_2 }, contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(32.dp)) {
                    Icon(Icons.Rounded.VisibilityOff, null, tint = Peach, modifier = Modifier.size(56.dp))
                    Spacer(Modifier.height(16.dp))
                    Text(s.lockedIn, style = MaterialTheme.typography.displaySmall, color = TextHigh)
                    Spacer(Modifier.height(8.dp))
                    Text(s.passPhone(names[1]), style = MaterialTheme.typography.bodyLarge, color = TextMid, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

private fun outcome(a: Rps?, b: Rps?, names: List<String>, twoPlayer: Boolean, s: com.umair.spinzy.i18n.Strings): String = when {
    a == null || b == null -> ""
    a == b -> s.draw
    a.beats(b) -> if (!twoPlayer) s.youWin else s.wins(names[0])
    else -> if (!twoPlayer) s.phoneWins else s.wins(names[1])
}

fun Rps.label(s: com.umair.spinzy.i18n.Strings) = when (this) { Rps.ROCK -> s.rock; Rps.PAPER -> s.paper; Rps.SCISSORS -> s.scissors }

@Composable
private fun Hand(choice: Rps?, colors: List<Color>, modifier: Modifier, upsideDown: Boolean = false) {
    val pop = remember(choice) { Animatable(if (choice == null) 1f else 0.3f) }
    androidx.compose.runtime.LaunchedEffect(choice) { pop.animateTo(1f, spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMediumLow)) }
    Box(
        modifier.size(140.dp).graphicsLayer { scaleX = pop.value; scaleY = pop.value }
            .clip(Squircle).background(Brush.linearGradient(colors)),
        contentAlignment = Alignment.Center,
    ) {
        if (choice == null) {
            Text("?", style = MaterialTheme.typography.displayLarge, color = Wine.copy(alpha = 0.55f))
        } else {
            RpsGlyph(choice, Wine, Modifier.size(78.dp).graphicsLayer { rotationZ = if (upsideDown) 180f else 0f })
        }
    }
}

@Composable
private fun ChoiceButton(c: Rps, modifier: Modifier, onClick: () -> Unit) {
    GlassCard(modifier.height(110.dp).pressable(pressedScale = 0.9f, onClick = onClick), shape = Squircle) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            RpsGlyph(c, Peach, Modifier.size(44.dp))
            Spacer(Modifier.height(6.dp))
            Text(c.label(LocalStrings.current), style = MaterialTheme.typography.labelLarge, color = TextHigh)
        }
    }
}

@Composable
private fun ModeChip(text: String, icon: ImageVector, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    val bg by animateColorAsState(if (selected) Peach else Color.Transparent, label = "mode")
    Row(
        modifier.height(44.dp).clip(RoundedCornerShape(50)).background(bg).pressable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
    ) {
        Icon(icon, null, tint = if (selected) Wine else TextMid, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.labelLarge, color = if (selected) Wine else TextMid)
    }
}

@Composable
private fun Scoreboard(names: List<String>, a: Int, b: Int) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        ScoreSide(names[0], a, Peach, Modifier.weight(1f), Alignment.Start)
        Text("vs", style = MaterialTheme.typography.titleMedium, color = TextLow)
        ScoreSide(names[1], b, Blush, Modifier.weight(1f), Alignment.End)
    }
}

@Composable
private fun ScoreSide(name: String, score: Int, color: Color, modifier: Modifier, align: Alignment.Horizontal) {
    Column(modifier, horizontalAlignment = align) {
        Text(name, style = MaterialTheme.typography.titleMedium, color = TextMid, maxLines = 1)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(top = 6.dp)) {
            repeat(WIN_TARGET) { i ->
                val on = i < score
                val c by animateColorAsState(if (on) color else Cream.copy(alpha = 0.12f), tween(300), label = "pip")
                Box(Modifier.size(14.dp).clip(CircleShape).background(c))
            }
        }
    }
}

