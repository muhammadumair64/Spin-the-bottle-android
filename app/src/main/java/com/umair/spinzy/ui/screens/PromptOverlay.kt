package com.umair.spinzy.ui.screens

import android.view.HapticFeedbackConstants
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.DirectionsRun
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.Psychology
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.ThumbDown
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.umair.spinzy.data.Player
import com.umair.spinzy.data.Prompt
import com.umair.spinzy.data.PromptType
import com.umair.spinzy.data.WildCard
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.ui.components.AppIcons
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.IconBadge
import com.umair.spinzy.ui.components.PlayerBubble
import com.umair.spinzy.ui.components.SquircleShape
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.theme.Blush
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.Mauve
import com.umair.spinzy.ui.theme.Midnight
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.PlayerColors
import com.umair.spinzy.ui.theme.Raspberry
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

private val CardShape = SquircleShape(5.5)

/**
 * Card v2:
 *  - swipe the card RIGHT = done (truth → believed), LEFT = skip; stamps fade in while dragging
 *  - one primary action; for a truth the group verdict is a second, separate step
 *  - skip / chicken out are quiet secondary actions
 */
@Composable
fun PromptOverlay(
    prompt: Prompt,
    player: Player,
    dareSeconds: Int,
    wild: WildCard?,
    partner: Player?,
    onDone: () -> Unit,
    onBusted: () -> Unit,
    onSkip: () -> Unit,
    onChicken: () -> Unit,
    onTick: () -> Unit,
) {
    val s = LocalStrings.current
    val isTruth = prompt.type == PromptType.TRUTH
    val back = if (isTruth) listOf(PeachLight, Blush, Mauve) else listOf(Peach, Coral, Raspberry)
    val front = if (isTruth) listOf(Color(0xFF7E2E6C), Mauve, Color(0xFFD889B4)) else listOf(Raspberry, Color(0xFFD64C6A), Coral)
    val pts = (if (isTruth) 1 else 2) * (if (wild == WildCard.DOUBLE) 2 else 1)
    val view = LocalView.current
    val rtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val scope = rememberCoroutineScope()

    val enter = remember { Animatable(0f) }
    val flip = remember { Animatable(0f) }
    val drag = remember { Animatable(0f) }             // px, + = right
    var verdict by remember(prompt.text) { mutableStateOf(false) }
    val showFront by remember { derivedStateOf { flip.value >= 90f } }
    val flipDone by remember { derivedStateOf { flip.value >= 179.5f } }
    LaunchedEffect(Unit) { enter.animateTo(1f, spring(dampingRatio = 0.62f, stiffness = Spring.StiffnessLow)) }
    LaunchedEffect(prompt.text) {
        drag.snapTo(0f)
        flip.snapTo(0f)
        flip.animateTo(180f, tween(780, delayMillis = 200, easing = FastOutSlowInEasing))
    }

    Box(
        Modifier.fillMaxSize()
            .background(Brush.verticalGradient(listOf(Midnight.copy(alpha = 0.80f), Midnight.copy(alpha = 0.94f))))
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {},
    ) {
        Column(
            Modifier.fillMaxSize().safeDrawingPadding().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PlayerBubble(player.avatar, null, PlayerColors[player.colorIndex % PlayerColors.size], size = 40.dp)
                Spacer(Modifier.width(10.dp))
                Text(player.name, style = MaterialTheme.typography.titleLarge, color = TextHigh)
                Spacer(Modifier.width(10.dp))
                Icon(Icons.Rounded.Star, null, tint = Peach, modifier = Modifier.size(18.dp))
                Text(" ${player.score}", style = MaterialTheme.typography.labelLarge, color = Peach)
                if (partner != null) {
                    Spacer(Modifier.width(12.dp))
                    Text(s.with(partner.name), style = MaterialTheme.typography.titleMedium, color = TextMid)
                }
            }
            if (wild != null) {
                Spacer(Modifier.height(10.dp))
                Row(
                    Modifier.clip(RoundedCornerShape(50)).background(wild.color.copy(alpha = 0.18f)).padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(AppIcons.wild(wild), null, tint = wild.color, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(s.wildTitle(wild), style = MaterialTheme.typography.labelLarge, color = wild.color)
                }
            }
            Spacer(Modifier.height(16.dp))

            // ---------- the swipeable flip card ----------
            BoxWithConstraints(Modifier.fillMaxWidth().height(390.dp)) {
                val widthPx = constraints.maxWidth.toFloat()
                val threshold = widthPx * 0.32f
                // done = swipe "forward" (right in LTR, left in RTL); a truth swiped forward counts as believed
                fun resolve(done: Boolean, toRight: Boolean) {
                    scope.launch {
                        drag.animateTo(if (toRight) widthPx * 1.4f else -widthPx * 1.4f, tween(260))
                        if (done) onDone() else onSkip()
                    }
                }
                Box(
                    Modifier.fillMaxSize()
                        .pointerInput(prompt.text, player.skipsLeft) {
                            var passed = false
                            detectHorizontalDragGestures(
                                onDragEnd = {
                                    val forward = if (rtl) drag.value < -threshold else drag.value > threshold
                                    val backward = if (rtl) drag.value > threshold else drag.value < -threshold
                                    when {
                                        forward -> resolve(done = true, toRight = !rtl)
                                        backward && player.skipsLeft > 0 -> resolve(done = false, toRight = rtl)
                                        else -> scope.launch { drag.animateTo(0f, spring(dampingRatio = 0.5f, stiffness = Spring.StiffnessMedium)) }
                                    }
                                    passed = false
                                },
                            ) { _, dx ->
                                if (!flipDone) return@detectHorizontalDragGestures
                                scope.launch { drag.snapTo(drag.value + dx) }
                                val over = abs(drag.value) > threshold
                                if (over != passed) { passed = over; if (over) view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK) }
                            }
                        }
                        .graphicsLayer {
                            val e = enter.value
                            scaleX = 0.7f + 0.3f * e; scaleY = 0.7f + 0.3f * e
                            translationY = (1f - e) * 300f
                            translationX = drag.value
                            rotationZ = (1f - e) * -8f + drag.value / widthPx * 14f
                            alpha = e.coerceIn(0f, 1f)
                            rotationY = flip.value
                            cameraDistance = 14f * density
                            shadowElevation = 40f; shape = CardShape; clip = true
                            ambientShadowColor = back[1]; spotShadowColor = back[1]
                        },
                ) {
                    if (!showFront) {
                        CardBack(isTruth, back)
                    } else {
                        Box(Modifier.fillMaxSize().graphicsLayer { rotationY = 180f }) {
                            CardFront(prompt, front, dareSeconds, startTimer = flipDone, onTick = onTick)
                            // swipe stamps
                            val p = (drag.value / threshold).coerceIn(-1f, 1f)
                            val forwardP = if (rtl) -p else p
                            Stamp(s.doneCaps, Icons.Rounded.Check, PeachLight, Modifier.align(Alignment.TopStart).padding(24.dp)
                                .graphicsLayer { alpha = forwardP.coerceAtLeast(0f); rotationZ = -12f; val k = 0.8f + 0.2f * forwardP.coerceAtLeast(0f); scaleX = k; scaleY = k })
                            Stamp(s.skipCaps, Icons.Rounded.SkipNext, Cream, Modifier.align(Alignment.TopEnd).padding(24.dp)
                                .graphicsLayer { alpha = (-forwardP).coerceAtLeast(0f); rotationZ = 12f; val k = 0.8f + 0.2f * (-forwardP).coerceAtLeast(0f); scaleX = k; scaleY = k })
                        }
                    }
                }
            }

            Spacer(Modifier.height(10.dp))
            SwipeHint(s.swipeCardHint)
            Spacer(Modifier.height(12.dp))

            // ---------- one primary action (truth: 2-step verdict) ----------
            AnimatedContent(
                targetState = isTruth && verdict,
                transitionSpec = { (fadeIn(tween(200)) + scaleIn(spring(dampingRatio = 0.6f), initialScale = 0.9f) + slideInVertically { it / 3 }) togetherWith fadeOut(tween(120)) },
                label = "primary",
            ) { askVerdict ->
                if (!askVerdict) {
                    GlowButton(
                        text = if (isTruth) s.answered else s.doneIt(pts),
                        colors = listOf(PeachLight, if (isTruth) Blush else Coral), textColor = Wine, icon = Icons.Rounded.Check,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { if (isTruth) verdict = true else onDone() },
                    )
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(s.groupVerdict, style = MaterialTheme.typography.titleMedium, color = TextHigh, textAlign = TextAlign.Center)
                        Spacer(Modifier.height(10.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            GlowButton(
                                s.believe(pts), colors = listOf(PeachLight, Blush), textColor = Wine, icon = Icons.Rounded.ThumbUp,
                                horizontalPadding = 12.dp, textStyle = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f), onClick = onDone,
                            )
                            GlowButton(
                                s.busted, colors = listOf(Wine, Raspberry), textColor = Cream, icon = Icons.Rounded.ThumbDown,
                                horizontalPadding = 12.dp, textStyle = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f), onClick = onBusted,
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                QuietAction(s.skipLeft(player.skipsLeft), Icons.Rounded.SkipNext, enabled = player.skipsLeft > 0, onSkip)
                QuietAction(s.chickenOut, Icons.AutoMirrored.Rounded.DirectionsRun, enabled = true, onChicken)
            }
        }
    }
}

@Composable
private fun Stamp(text: String, icon: ImageVector, color: Color, modifier: Modifier) {
    Row(
        modifier.border(3.dp, color, RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, null, tint = color, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(6.dp))
        Text(text, style = MaterialTheme.typography.headlineMedium, color = color)
    }
}

/** Arrows that nudge left / right to teach the swipe. */
@Composable
private fun SwipeHint(text: String) {
    val t = rememberInfiniteTransition(label = "swipe")
    val x by t.animateFloat(-6f, 6f, infiniteRepeatable(tween(700), RepeatMode.Reverse), label = "x")
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.AutoMirrored.Rounded.ArrowBack, null, tint = TextLow, modifier = Modifier.size(16.dp).graphicsLayer { translationX = -x })
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = TextLow, textAlign = TextAlign.Center)
        Spacer(Modifier.width(8.dp))
        Icon(Icons.AutoMirrored.Rounded.ArrowForward, null, tint = TextLow, modifier = Modifier.size(16.dp).graphicsLayer { translationX = x })
    }
}

@Composable
private fun CardBack(isTruth: Boolean, colors: List<Color>) {
    val s = LocalStrings.current
    Box(Modifier.fillMaxSize().background(Brush.linearGradient(colors)), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            for (i in 1..7) drawCircle(Color.White.copy(alpha = 0.08f), radius = size.minDimension * 0.11f * i, style = Stroke(3f))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconBadge(if (isTruth) Icons.Rounded.Psychology else Icons.Rounded.Bolt, size = 72.dp, bg = Wine)
            Spacer(Modifier.height(14.dp))
            Text(if (isTruth) s.truth else s.dare, style = MaterialTheme.typography.displayLarge, color = Wine)
        }
    }
}

@Composable
private fun CardFront(prompt: Prompt, colors: List<Color>, dareSeconds: Int, startTimer: Boolean, onTick: () -> Unit) {
    val s = LocalStrings.current
    Box(
        Modifier.fillMaxSize()
            .background(Brush.linearGradient(colors, start = Offset(0f, 0f), end = Offset(900f, 1400f)))
            .background(Brush.radialGradient(listOf(Color.White.copy(alpha = 0.18f), Color.Transparent), center = Offset(0f, 0f), radius = 900f)),
    ) {
        Column(Modifier.fillMaxSize().padding(horizontal = 30.dp, vertical = 30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            if (prompt.type == PromptType.DARE) {
                DareTimer(prompt.text, dareSeconds, startTimer, onTick)
            } else {
                Text(s.truthCaps, style = MaterialTheme.typography.labelMedium, color = Cream.copy(alpha = 0.8f))
            }
            Spacer(Modifier.weight(1f))
            Text(prompt.text, style = MaterialTheme.typography.headlineLarge, color = Cream, textAlign = TextAlign.Center)
            Spacer(Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(prompt.pack?.let { AppIcons.pack(it) } ?: Icons.Rounded.EditNote, null, tint = Cream.copy(alpha = 0.75f), modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text(prompt.pack?.let { s.packTitle(it) } ?: s.custom, style = MaterialTheme.typography.labelLarge, color = Cream.copy(alpha = 0.75f))
            }
        }
    }
}

/** "00:30" countdown like a stopwatch + thin progress line. */
@Composable
private fun DareTimer(key: String, seconds: Int, start: Boolean, onTick: () -> Unit) {
    val s = LocalStrings.current
    val progress = remember(key) { Animatable(1f) }
    var left by remember(key) { mutableIntStateOf(seconds) }
    LaunchedEffect(key, start) {
        if (!start) return@LaunchedEffect
        launch { progress.animateTo(0f, tween(seconds * 1000, easing = LinearEasing)) }
        while (left > 0) {
            delay(1000)
            left--
            if (left <= 5) onTick()
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Rounded.Timer, null, tint = Cream, modifier = Modifier.size(26.dp))
            Spacer(Modifier.width(8.dp))
            Text(
                if (left == 0) s.timesUp else "%02d:%02d".format(left / 60, left % 60),
                style = MaterialTheme.typography.displaySmall, color = if (left in 1..5) PeachLight else Cream,
            )
        }
        Spacer(Modifier.height(10.dp))
        Box(Modifier.width(120.dp).height(4.dp).clip(RoundedCornerShape(50)).background(Cream.copy(alpha = 0.2f))) {
            Box(Modifier.fillMaxWidth(progress.value).height(4.dp).clip(RoundedCornerShape(50)).background(Cream))
        }
    }
}

@Composable
private fun QuietAction(text: String, icon: ImageVector, enabled: Boolean, onClick: () -> Unit) {
    Row(
        Modifier.clip(RoundedCornerShape(50)).graphicsLayer { alpha = if (enabled) 1f else 0.4f }
            .pressable(enabled = enabled, onClick = onClick).padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, null, tint = TextMid, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(6.dp))
        Text(text, style = MaterialTheme.typography.labelLarge, color = TextMid)
    }
}
