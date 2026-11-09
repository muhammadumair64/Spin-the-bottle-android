package com.umair.spinzy.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/** Staggered entrance: element springs up from below while fading in. */
@Composable
fun Modifier.enterUp(delayMillis: Int = 0, distance: Int = 28): Modifier {
    val progress = remember { Animatable(0f) }
    val px = with(LocalDensity.current) { distance.dp.toPx() }
    LaunchedEffect(Unit) {
        delay(delayMillis.toLong())
        progress.animateTo(1f, spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessLow))
    }
    return graphicsLayer {
        val p = progress.value
        alpha = p.coerceIn(0f, 1f)
        translationY = (1f - p) * px
        val s = 0.96f + 0.04f * p
        scaleX = s; scaleY = s
    }
}
