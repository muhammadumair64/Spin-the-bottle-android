package com.umair.spinzy.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.rotate
import com.umair.spinzy.ui.theme.PlayerColors
import kotlin.math.sin
import kotlin.random.Random

private class Piece(
    val x: Float, val speed: Float, val drift: Float, val size: Float,
    val spin: Float, val colorIndex: Int, val delay: Float, val wobble: Float,
)

/** Lightweight frame-driven confetti rain (no libraries). */
@Composable
fun ConfettiRain(modifier: Modifier = Modifier, count: Int = 120) {
    val pieces = remember {
        List(count) {
            Piece(
                x = Random.nextFloat(), speed = 0.18f + Random.nextFloat() * 0.25f, drift = Random.nextFloat() * 2f - 1f,
                size = 10f + Random.nextFloat() * 14f, spin = 90f + Random.nextFloat() * 360f,
                colorIndex = Random.nextInt(PlayerColors.size), delay = Random.nextFloat() * 1.8f, wobble = Random.nextFloat() * 6f,
            )
        }
    }
    var nanos by remember { mutableLongStateOf(0L) }
    LaunchedEffect(Unit) {
        val start = withFrameNanos { it }
        while (true) withFrameNanos { nanos = it - start }
    }
    Canvas(modifier) {
        val t = nanos / 1_000_000_000f
        pieces.forEach { p ->
            val life = t - p.delay
            if (life < 0f) return@forEach
            val y = ((life * p.speed) % 1.2f) * size.height - 40f
            val x = p.x * size.width + sin(life * 2f + p.wobble) * 30f + p.drift * life * 20f
            rotate(life * p.spin, pivot = Offset(x, y)) {
                drawRect(PlayerColors[p.colorIndex], topLeft = Offset(x, y), size = Size(p.size, p.size * 0.5f))
            }
        }
    }
}
