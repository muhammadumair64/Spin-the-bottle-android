package com.umair.spinzy.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.umair.spinzy.ui.theme.Raspberry
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine

/**
 * Squircle avatar with a vector icon. [highlighted] makes it pulse & glow, [dimmed] fades it.
 * [streak] ≥ 2 shows a small flame badge.
 */
@Composable
fun PlayerBubble(
    avatar: String,
    name: String?,
    color: Color,
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    highlighted: Boolean = false,
    dimmed: Boolean = false,
    labelOnTop: Boolean = false,
    streak: Int = 0,
) {
    val pulse = rememberInfiniteTransition(label = "pulse")
    val p by pulse.animateFloat(0f, 1f, infiniteRepeatable(tween(900), RepeatMode.Reverse), label = "p")
    val scale by animateFloatAsState(
        when { highlighted -> 1.22f; dimmed -> 0.88f; else -> 1f },
        spring(dampingRatio = 0.45f, stiffness = Spring.StiffnessLow), label = "scale",
    )
    val alpha by animateFloatAsState(if (dimmed) 0.35f else 1f, tween(400), label = "alpha")

    Column(
        modifier.graphicsLayer { scaleX = scale; scaleY = scale; this.alpha = alpha },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (name != null && labelOnTop) {
            Label(name, highlighted, size)
            Spacer(Modifier.height(6.dp))
        }
        Box(
            Modifier
                .size(size)
                .drawBehind {
                    if (highlighted) {
                        val r = this.size.minDimension * (0.85f + 0.25f * p)
                        drawCircle(Brush.radialGradient(listOf(color.copy(alpha = 0.65f), Color.Transparent), radius = r), radius = r)
                    }
                }
                .background(Brush.linearGradient(listOf(color, color.copy(alpha = 0.72f))), Squircle)
                .border(1.5.dp, Color.White.copy(alpha = if (highlighted) 0.85f else 0.25f), Squircle),
            contentAlignment = Alignment.Center,
        ) {
            Icon(AppIcons.avatar(avatar), contentDescription = name, tint = Wine, modifier = Modifier.size(size * 0.5f))
            if (streak >= 2) {
                Row(
                    Modifier.align(Alignment.TopEnd).offset(x = 8.dp, y = (-6).dp)
                        .background(Raspberry, RoundedCornerShape(50)).padding(horizontal = 5.dp, vertical = 1.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Rounded.LocalFireDepartment, null, tint = TextHigh, modifier = Modifier.size(11.dp))
                    Text("$streak", style = MaterialTheme.typography.labelSmall, color = TextHigh)
                }
            }
        }
        if (name != null && !labelOnTop) {
            Spacer(Modifier.height(6.dp))
            Label(name, highlighted, size)
        }
    }
}

@Composable
private fun Label(name: String, highlighted: Boolean, size: Dp) {
    Text(
        name,
        style = MaterialTheme.typography.titleMedium,
        color = if (highlighted) TextHigh else TextMid,
        maxLines = 1, overflow = TextOverflow.Ellipsis,
        modifier = Modifier.widthIn(max = size + 32.dp),
    )
}

