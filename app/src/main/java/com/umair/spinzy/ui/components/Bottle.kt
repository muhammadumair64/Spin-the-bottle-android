package com.umair.spinzy.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import com.umair.spinzy.data.BottleSkin

/**
 * A stylised neon glass bottle drawn entirely with Canvas (no image assets),
 * pointing UP. Rotate it from the outside with graphicsLayer.
 * Aspect ratio ≈ 1 : 2.9 (width : height).
 */
@Composable
fun NeonBottle(modifier: Modifier = Modifier, glow: Float = 1f, skin: BottleSkin = BottleSkin.NEON) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height
        val neckW = w * 0.34f
        val neckTop = h * 0.06f
        val shoulder = h * 0.40f
        val bodyTop = h * 0.52f
        val r = w * 0.22f
        val cx = w / 2f

        val bottle = Path().apply {
            moveTo(cx - neckW / 2, neckTop)
            lineTo(cx - neckW / 2, shoulder)
            cubicTo(cx - neckW / 2, bodyTop - h * 0.05f, 0f, bodyTop - h * 0.06f, 0f, bodyTop + h * 0.02f)
            lineTo(0f, h - r)
            quadraticTo(0f, h, r, h)
            lineTo(w - r, h)
            quadraticTo(w, h, w, h - r)
            lineTo(w, bodyTop + h * 0.02f)
            cubicTo(w, bodyTop - h * 0.06f, cx + neckW / 2, bodyTop - h * 0.05f, cx + neckW / 2, shoulder)
            lineTo(cx + neckW / 2, neckTop)
            close()
        }

        // Outer glow
        for (i in 3 downTo 1) {
            drawPath(bottle, skin.glow.copy(alpha = (0.07f * glow * i).coerceAtMost(1f)), style = Stroke(width = w * 0.08f * i))
        }

        // Glass body
        drawPath(
            bottle,
            Brush.verticalGradient(skin.glass, startY = 0f, endY = h),
        )

        // Liquid inside (lower part) + its surface line
        clipPath(bottle) {
            val level = h * 0.66f
            drawRect(
                Brush.verticalGradient(skin.liquid, startY = level, endY = h),
                topLeft = Offset(0f, level), size = Size(w, h - level),
            )
            drawLine(Color.White.copy(alpha = 0.5f), Offset(0f, level), Offset(w, level), strokeWidth = w * 0.03f)
            // Vertical shine
            drawRoundRect(
                Brush.horizontalGradient(listOf(Color.White.copy(alpha = 0.55f), Color.Transparent)),
                topLeft = Offset(w * 0.16f, bodyTop), size = Size(w * 0.16f, h * 0.40f),
                cornerRadius = CornerRadius(w, w),
            )
            drawRoundRect(
                Color.White.copy(alpha = 0.35f),
                topLeft = Offset(cx - neckW * 0.28f, neckTop + h * 0.04f), size = Size(neckW * 0.16f, h * 0.24f),
                cornerRadius = CornerRadius(w, w),
            )
        }

        // Crisp outline
        drawPath(bottle, Color.White.copy(alpha = 0.55f), style = Stroke(width = w * 0.025f))

        // Lip / cap — the "pointer" that picks a player
        drawRoundRect(
            skin.cap,
            topLeft = Offset(cx - neckW * 0.62f, 0f), size = Size(neckW * 1.24f, neckTop + h * 0.025f),
            cornerRadius = CornerRadius(w * 0.08f, w * 0.08f),
        )
        drawCircle(
            Brush.radialGradient(listOf(skin.cap.copy(alpha = (0.7f * glow).coerceAtMost(1f)), Color.Transparent), center = Offset(cx, neckTop / 2), radius = w * 0.6f),
            radius = w * 0.6f, center = Offset(cx, neckTop / 2),
        )
    }
}
