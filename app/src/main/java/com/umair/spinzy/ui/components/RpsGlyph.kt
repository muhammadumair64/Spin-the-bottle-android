package com.umair.spinzy.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCut
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

enum class Rps {
    ROCK, PAPER, SCISSORS;

    fun beats(o: Rps) = (this == ROCK && o == SCISSORS) || (this == PAPER && o == ROCK) || (this == SCISSORS && o == PAPER)
}

/** Vector glyphs for Rock / Paper / Scissors. Rock is a custom-drawn pebble. */
@Composable
fun RpsGlyph(choice: Rps, tint: Color, modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        when (choice) {
            Rps.ROCK -> Canvas(Modifier.fillMaxSize()) {
                val w = size.width; val h = size.height
                val p = Path().apply {
                    moveTo(w * 0.18f, h * 0.62f)
                    cubicTo(w * 0.10f, h * 0.40f, w * 0.30f, h * 0.20f, w * 0.48f, h * 0.22f)
                    cubicTo(w * 0.62f, h * 0.14f, w * 0.86f, h * 0.30f, w * 0.86f, h * 0.50f)
                    cubicTo(w * 0.94f, h * 0.68f, w * 0.78f, h * 0.84f, w * 0.56f, h * 0.82f)
                    cubicTo(w * 0.40f, h * 0.88f, w * 0.22f, h * 0.80f, w * 0.18f, h * 0.62f)
                    close()
                }
                drawPath(p, tint)
                // facets / cracks give it a chiselled look
                val crack = Stroke(width = w * 0.035f, cap = StrokeCap.Round)
                val shade = Color.Black.copy(alpha = 0.22f)
                drawLine(shade, Offset(w * 0.48f, h * 0.24f), Offset(w * 0.44f, h * 0.48f), crack.width, StrokeCap.Round)
                drawLine(shade, Offset(w * 0.44f, h * 0.48f), Offset(w * 0.60f, h * 0.62f), crack.width, StrokeCap.Round)
                drawLine(shade, Offset(w * 0.44f, h * 0.48f), Offset(w * 0.26f, h * 0.56f), crack.width, StrokeCap.Round)
                drawCircle(Color.White.copy(alpha = 0.28f), radius = w * 0.07f, center = Offset(w * 0.34f, h * 0.36f))
            }
            Rps.PAPER -> Icon(Icons.Rounded.Description, null, tint = tint, modifier = Modifier.fillMaxSize())
            Rps.SCISSORS -> Icon(Icons.Rounded.ContentCut, null, tint = tint, modifier = Modifier.fillMaxSize())
        }
    }
}
