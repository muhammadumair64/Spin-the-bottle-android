package com.umair.spinzy.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.umair.spinzy.audio.LocalSfx
import com.umair.spinzy.audio.Sfx
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.ui.components.NeonBottle
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.TextHigh
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** Animated intro: bottle spins in, ripples spread, the name rises letter by letter. ~2 s. */
@Composable
fun SplashScreen(state: GameState, vm: GameViewModel) {
    val s = LocalStrings.current
    val sfx = LocalSfx.current
    val bottleScale = remember { Animatable(0f) }
    val bottleRot = remember { Animatable(-540f) }
    val ripple = remember { Animatable(0f) }
    val letters = remember { "Spinzy".map { Animatable(0f) } }
    val tagline = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        launch { bottleScale.animateTo(1f, spring(dampingRatio = 0.5f, stiffness = Spring.StiffnessLow)) }
        launch { bottleRot.animateTo(30f, tween(1100, easing = FastOutSlowInEasing)) }
        launch { delay(150); sfx?.play(Sfx.WHOOSH, 0.5f) }
        launch { delay(700); ripple.animateTo(1f, tween(1200)) }
        letters.forEachIndexed { i, a -> launch { delay(650L + i * 70L); a.animateTo(1f, spring(dampingRatio = 0.45f, stiffness = Spring.StiffnessMediumLow)) } }
        launch { delay(1150); tagline.animateTo(1f, tween(500)) }
        delay(1150); sfx?.play(Sfx.REVEAL, 0.5f)
        delay(900)
        while (!vm.state.value.loaded) delay(50)
        vm.splashFinished()
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(420.dp)) {
            for (i in 0 until 3) {
                val p = (ripple.value - i * 0.18f).coerceIn(0f, 1f)
                if (p > 0f) drawCircle(Cream.copy(alpha = 0.18f * (1f - p)), radius = size.minDimension * 0.15f + size.minDimension * 0.35f * p, style = Stroke(3f))
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            NeonBottle(
                Modifier.size(64.dp, 186.dp).graphicsLayer {
                    scaleX = bottleScale.value; scaleY = bottleScale.value; rotationZ = bottleRot.value
                },
                skin = state.settings.skin,
            )
            Spacer(Modifier.height(30.dp))
            Row {
                "Spinzy".forEachIndexed { i, c ->
                    Text(
                        c.toString(), style = MaterialTheme.typography.displayLarge, color = TextHigh,
                        modifier = Modifier.graphicsLayer {
                            val p = letters[i].value
                            alpha = p.coerceIn(0f, 1f); translationY = (1f - p) * 60f; rotationZ = (1f - p) * 12f
                        },
                    )
                }
            }
            Text(
                s.tagline, style = MaterialTheme.typography.titleLarge.copy(fontStyle = FontStyle.Italic), color = Peach,
                modifier = Modifier.graphicsLayer { alpha = tagline.value; translationY = (1f - tagline.value) * 20f },
            )
        }
    }
}
