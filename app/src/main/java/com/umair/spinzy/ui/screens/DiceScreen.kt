package com.umair.spinzy.ui.screens

import com.umair.spinzy.i18n.LocalStrings
import android.view.HapticFeedbackConstants
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Casino
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.umair.spinzy.audio.LocalSfx
import com.umair.spinzy.audio.Sfx
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.ui.components.GlassCard
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.OnShake
import com.umair.spinzy.ui.components.SpinzyTopBar
import com.umair.spinzy.ui.components.Squircle
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.CoralDeep
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun DiceScreen(state: GameState, vm: GameViewModel) {
    val s = LocalStrings.current
    var count by remember { mutableIntStateOf(2) }
    val faces = remember { mutableStateListOf(5, 2) }
    var rolling by remember { mutableStateOf(false) }
    var total by remember { mutableStateOf<Int?>(null) }
    val history = remember { mutableStateListOf<Int>() }
    val anims = remember { List(2) { Triple(Animatable(0f), Animatable(0f), Animatable(1f)) } } // rotation, jumpY, scale
    val scope = rememberCoroutineScope()
    val sfx = LocalSfx.current
    val view = LocalView.current

    fun roll() {
        if (rolling) return
        rolling = true; total = null
        sfx?.play(Sfx.WHOOSH, 0.6f, 1.5f)
        scope.launch {
            // tumble each die with its own timing
            for (i in 0 until count) {
                val (r, y, s) = anims[i]
                launch {
                    val dir = if (Random.nextBoolean()) 1f else -1f
                    launch { r.animateTo(r.value + dir * (540f + 90f * Random.nextInt(0, 3)), tween(900 + i * 120, easing = FastOutSlowInEasing)) }
                    launch {
                        y.animateTo(-1f, tween(260)); y.animateTo(0f, spring(dampingRatio = 0.35f, stiffness = Spring.StiffnessMedium))
                    }
                    s.animateTo(1.15f, tween(200)); s.animateTo(1f, spring(dampingRatio = 0.4f))
                }
            }
            // flicker faces while tumbling
            repeat(11) {
                for (i in 0 until count) faces[i] = Random.nextInt(1, 7)
                sfx?.play(Sfx.TICK, 0.35f, 0.7f + Random.nextFloat() * 0.6f)
                delay(80)
            }
            for (i in 0 until count) faces[i] = Random.nextInt(1, 7)
            delay(200)
            sfx?.play(Sfx.LAND, 0.8f, 1.2f)
            if (state.settings.haptics) view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            val sum = (0 until count).sumOf { faces[it] }
            total = sum
            history.add(0, sum); if (history.size > 14) history.removeAt(history.lastIndex)
            rolling = false
        }
    }
    OnShake(state.settings.shakeToSpin) { roll() }

    Column(Modifier.fillMaxSize().safeDrawingPadding().padding(horizontal = 20.dp)) {
        SpinzyTopBar(s.dice, s.diceHint, onBack = { vm.back() })
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(50)).background(Cream.copy(alpha = 0.07f)).padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            listOf(1, 2).forEach { n ->
                val sel = count == n
                val bg by animateColorAsState(if (sel) Peach else Color.Transparent, label = "n")
                Box(Modifier.weight(1f).height(44.dp).clip(RoundedCornerShape(50)).background(bg).pressable { if (!rolling) { count = n; total = null } }, contentAlignment = Alignment.Center) {
                    Text(if (n == 1) s.oneDie else s.twoDice, style = MaterialTheme.typography.labelLarge, color = if (sel) Wine else TextMid)
                }
            }
        }

        Row(
            Modifier.fillMaxWidth().weight(1f).pressable(pressedScale = 0.97f) { roll() },
            horizontalArrangement = Arrangement.spacedBy(28.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (i in 0 until count) {
                val (r, y, s) = anims[i]
                Die(
                    faces[i],
                    Modifier.size(if (count == 1) 150.dp else 120.dp).graphicsLayer {
                        rotationZ = r.value; translationY = y.value * 160f; scaleX = s.value; scaleY = s.value
                        shadowElevation = 30f; shape = Squircle; clip = true
                    },
                )
            }
        }

        AnimatedContent(
            targetState = total,
            transitionSpec = { (fadeIn() + scaleIn(spring(dampingRatio = 0.45f), initialScale = 0.6f)) togetherWith fadeOut(tween(100)) },
            label = "total", modifier = Modifier.fillMaxWidth(),
        ) { t ->
            Text(
                if (t == null) (if (rolling) s.rolling else s.ready) else if (count == 1) "$t" else s.total(t),
                style = MaterialTheme.typography.displaySmall, color = if (t == null) TextMid else Peach,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(Modifier.height(12.dp))
        if (history.isNotEmpty()) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(history) { i, v ->
                    GlassCard(Modifier.graphicsLayer { alpha = 1f - i * 0.05f }, shape = RoundedCornerShape(50)) {
                        Text("$v", style = MaterialTheme.typography.labelLarge, color = if (i == 0) Peach else TextLow, modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp))
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
        }
        GlowButton(s.roll, colors = listOf(Coral, CoralDeep), enabled = !rolling, icon = Icons.Rounded.Casino, modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) { roll() }
    }
}

/** Squircle die with wine pips. */
@Composable
private fun Die(value: Int, modifier: Modifier) {
    Box(modifier.background(Brush.linearGradient(listOf(Cream, PeachLight, Peach))), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize().padding(22.dp)) {
            val r = size.minDimension * 0.11f
            fun pip(x: Float, y: Float) {
                val c = Offset(size.width * x, size.height * y)
                drawCircle(Wine, r, c)
                drawCircle(Color.White.copy(alpha = 0.25f), r * 0.35f, c + Offset(-r * 0.3f, -r * 0.3f))
            }
            val l = 0.12f; val m = 0.5f; val h = 0.88f
            when (value) {
                1 -> pip(m, m)
                2 -> { pip(l, l); pip(h, h) }
                3 -> { pip(l, l); pip(m, m); pip(h, h) }
                4 -> { pip(l, l); pip(h, l); pip(l, h); pip(h, h) }
                5 -> { pip(l, l); pip(h, l); pip(m, m); pip(l, h); pip(h, h) }
                else -> { pip(l, l); pip(h, l); pip(l, m); pip(h, m); pip(l, h); pip(h, h) }
            }
        }
    }
}

