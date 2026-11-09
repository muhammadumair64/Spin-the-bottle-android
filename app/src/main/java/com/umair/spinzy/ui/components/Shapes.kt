package com.umair.spinzy.ui.components

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sin

/**
 * Superellipse ("squircle") — the soft, pillowy tile shape used across the Velvet theme.
 * [n] = 2 is an ellipse; 4–5 is a classic squircle; higher = boxier.
 */
class SquircleShape(private val n: Double = 4.2) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val a = size.width / 2f
        val b = size.height / 2f
        val path = Path()
        val steps = 96
        for (i in 0..steps) {
            val t = 2 * Math.PI * i / steps
            val c = cos(t); val s = sin(t)
            val x = a + a * (sign(c) * abs(c).pow(2.0 / n)).toFloat()
            val y = b + b * (sign(s) * abs(s).pow(2.0 / n)).toFloat()
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        path.close()
        return Outline.Generic(path)
    }
}

val Squircle = SquircleShape()
