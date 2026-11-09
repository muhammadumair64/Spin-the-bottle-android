package com.umair.spinzy.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.umair.spinzy.ui.theme.Berry
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.Plum900
import com.umair.spinzy.ui.theme.Plum950
import com.umair.spinzy.ui.theme.Wine
import kotlin.math.cos
import kotlin.math.sin

/**
 * Velvet background: plum→wine gradient, a slowly drifting warm glow tinted by [accent],
 * and faint concentric rings that breathe — all drawn in the draw phase (no recomposition).
 */
@Composable
fun AuroraBackground(
    modifier: Modifier = Modifier,
    accent: Color = Coral,
    content: @Composable BoxScope.() -> Unit,
) {
    val t = rememberInfiniteTransition(label = "velvet")
    val phase by t.animateFloat(0f, (2 * Math.PI).toFloat(), infiniteRepeatable(tween(22_000, easing = LinearEasing)), label = "phase")
    val breathe by t.animateFloat(0.92f, 1.08f, infiniteRepeatable(tween(5_000), RepeatMode.Reverse), label = "breathe")
    val accentColor by animateColorAsState(accent, tween(900), label = "accent")

    Box(modifier.fillMaxSize()) {
        Canvas(Modifier.fillMaxSize()) {
            drawRect(Brush.verticalGradient(listOf(Wine, Plum900, Plum950)))
            // warm glow (top-left, drifting)
            val g1 = Offset(size.width * (0.15f + 0.08f * cos(phase)), size.height * (0.12f + 0.05f * sin(phase)))
            val r1 = size.maxDimension * 0.55f * breathe
            drawCircle(Brush.radialGradient(listOf(accentColor.copy(alpha = 0.42f), Color.Transparent), g1, r1), r1, g1)
            // berry glow (bottom-right)
            val g2 = Offset(size.width * (0.95f + 0.05f * sin(phase)), size.height * (0.85f + 0.04f * cos(phase)))
            val r2 = size.maxDimension * 0.5f
            drawCircle(Brush.radialGradient(listOf(Berry.copy(alpha = 0.55f), Color.Transparent), g2, r2), r2, g2)
            // concentric rings, like ripples on velvet
            val rc = Offset(size.width * 1.05f, size.height * 0.38f)
            for (i in 1..7) {
                drawCircle(
                    Cream.copy(alpha = 0.035f), radius = size.width * 0.22f * i * breathe, center = rc,
                    style = Stroke(width = 2f),
                )
            }
        }
        content()
    }
}
