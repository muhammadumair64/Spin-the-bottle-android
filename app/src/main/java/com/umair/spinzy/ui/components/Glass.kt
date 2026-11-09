package com.umair.spinzy.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.GlassStroke
import com.umair.spinzy.ui.theme.TextHigh

/** Soft velvet surface used for every card in the app. */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(28.dp),
    tint: Color = Color.Transparent,
    borderColor: Color = GlassStroke,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier
            .clip(shape)
            .background(Brush.linearGradient(listOf(Cream.copy(alpha = 0.09f), Cream.copy(alpha = 0.03f))))
            .background(tint.copy(alpha = tint.alpha * 0.16f))
            .border(BorderStroke(1.dp, Brush.verticalGradient(listOf(borderColor, borderColor.copy(alpha = 0.02f)))), shape),
        content = content,
    )
}

/** Springy press-to-shrink modifier — gives every tappable thing a tactile feel. */
@Composable
fun Modifier.pressable(
    enabled: Boolean = true,
    pressedScale: Float = 0.94f,
    haptic: Boolean = true,
    onClick: () -> Unit,
): Modifier {
    val source = remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val scale by animateFloatAsState(
        if (pressed && enabled) pressedScale else 1f,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium),
        label = "press",
    )
    val hf = LocalHapticFeedback.current
    return this
        .graphicsLayer { scaleX = scale; scaleY = scale }
        .clickable(interactionSource = source, indication = null, enabled = enabled) {
            if (haptic) hf.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            onClick()
        }
}

/** Gradient pill button with a soft colored glow and optional leading vector icon. */
@Composable
fun GlowButton(
    text: String,
    modifier: Modifier = Modifier,
    colors: List<Color>,
    enabled: Boolean = true,
    height: Dp = 62.dp,
    textColor: Color = TextHigh,
    horizontalPadding: Dp = 28.dp,
    textStyle: TextStyle? = null,
    icon: ImageVector? = null,
    onClick: () -> Unit,
) {
    val alpha by animateFloatAsState(if (enabled) 1f else 0.4f, label = "enabled")
    val shape = RoundedCornerShape(50)
    Row(
        modifier
            .graphicsLayer { this.alpha = alpha }
            .pressable(enabled = enabled, onClick = onClick)
            .height(height)
            .shadow(if (enabled) 22.dp else 0.dp, shape, ambientColor = colors.first(), spotColor = colors.first())
            .clip(shape)
            .background(Brush.linearGradient(colors))
            .border(1.dp, Color.White.copy(alpha = 0.18f), shape)
            .padding(horizontal = horizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, tint = textColor, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(10.dp))
        }
        Text(text, style = textStyle ?: MaterialTheme.typography.titleLarge, color = textColor, maxLines = 1)
    }
}

/** Small round icon button. */
@Composable
fun GlassIconButton(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    GlassCard(modifier.pressable(onClick = onClick).size(size), shape = RoundedCornerShape(50)) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center, content = content)
    }
}

/** Round icon "badge" (e.g. the ♀/♂ circles in the reference): solid wine disc + cream icon. */
@Composable
fun IconBadge(icon: ImageVector, modifier: Modifier = Modifier, size: Dp = 44.dp, bg: Color = Color(0xFF6A1645), tint: Color = Cream) {
    Box(
        modifier.size(size).clip(RoundedCornerShape(50))
            .background(Brush.linearGradient(listOf(bg, bg.copy(alpha = 0.8f)))),
        contentAlignment = Alignment.Center,
    ) { Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(size * 0.52f)) }
}

/** Plain height spacer helper. */
@Composable fun VSpace(h: Dp) = Spacer(Modifier.height(h))
