package com.umair.spinzy.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.umair.spinzy.game.ToastTone
import com.umair.spinzy.game.UiEvent
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.i18n.Strings
import com.umair.spinzy.ui.theme.Blush
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.CoralDeep
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.MidnightRaised
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ───────────────────────────── Toasts ─────────────────────────────

/** Holds the toast currently on screen. New toasts replace the old one with an animation. */
@Stable
class ToasterState {
    var current by mutableStateOf<UiEvent.Toast?>(null)
        private set

    fun show(t: UiEvent.Toast) { current = t }
    fun show(tone: ToastTone, text: (Strings) -> String) = show(UiEvent.Toast(tone, text))
    fun dismiss(id: Long) { if (current?.id == id) current = null }
}

val LocalToaster = staticCompositionLocalOf { ToasterState() }

private fun ToastTone.icon(): ImageVector = when (this) {
    ToastTone.SUCCESS -> Icons.Rounded.CheckCircle
    ToastTone.INFO -> Icons.Rounded.Info
    ToastTone.WARNING -> Icons.Rounded.WarningAmber
    ToastTone.FIRE -> Icons.Rounded.LocalFireDepartment
}

private fun ToastTone.color(): Color = when (this) {
    ToastTone.SUCCESS -> PeachLight
    ToastTone.INFO -> Blush
    ToastTone.WARNING -> Peach
    ToastTone.FIRE -> Coral
}

/** Animated toast that drops in from the top, auto-dismisses (with a shrinking timer line) and can be swiped away. */
@Composable
fun ToastHost(state: ToasterState, modifier: Modifier = Modifier) {
    val s = LocalStrings.current
    Box(modifier.fillMaxSize().statusBarsPadding().padding(top = 8.dp, start = 16.dp, end = 16.dp), contentAlignment = Alignment.TopCenter) {
        val t = state.current
        AnimatedVisibility(
            visible = t != null,
            enter = slideInVertically(spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow)) { -it * 2 } + fadeIn(),
            exit = slideOutVertically(tween(220)) { -it * 2 } + fadeOut(tween(200)),
        ) {
            // keep the last toast while exiting
            val toast = remember { mutableStateOf(t) }
            if (t != null) toast.value = t
            val shown = toast.value ?: return@AnimatedVisibility
            val life = remember(shown.id) { Animatable(1f) }
            val pop = remember(shown.id) { Animatable(0.6f) }
            LaunchedEffect(shown.id) {
                launch { pop.animateTo(1f, spring(dampingRatio = 0.35f, stiffness = Spring.StiffnessMedium)) }
                life.animateTo(0f, tween(if (shown.action != null) 4200 else 2600, easing = LinearEasing))
                state.dismiss(shown.id)
            }
            val dragY = remember { Animatable(0f) }
            val scope = rememberCoroutineScope()
            val tone = shown.tone
            Column(
                Modifier.widthIn(max = 480.dp).fillMaxWidth()
                    .graphicsLayer { translationY = dragY.value.coerceAtMost(0f) }
                    .pointerInput(shown.id) {
                        detectVerticalDragGestures(
                            onDragEnd = { scope.launch { if (dragY.value < -40f) state.dismiss(shown.id) else dragY.animateTo(0f, spring()) } },
                        ) { _, d -> scope.launch { dragY.snapTo(dragY.value + d) } }
                    }
                    .shadow(24.dp, RoundedCornerShape(24.dp), ambientColor = tone.color(), spotColor = tone.color())
                    .clip(RoundedCornerShape(24.dp))
                    .background(Brush.linearGradient(listOf(MidnightRaised, Wine)))
                    .border(1.dp, tone.color().copy(alpha = 0.35f), RoundedCornerShape(24.dp)),
            ) {
                Row(Modifier.padding(start = 14.dp, end = 8.dp, top = 12.dp, bottom = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.size(34.dp).graphicsLayer { scaleX = pop.value; scaleY = pop.value; rotationZ = (1f - pop.value) * -60f }
                            .clip(RoundedCornerShape(50)).background(tone.color().copy(alpha = 0.18f)),
                        contentAlignment = Alignment.Center,
                    ) { Icon(tone.icon(), null, tint = tone.color(), modifier = Modifier.size(20.dp)) }
                    Spacer(Modifier.width(12.dp))
                    Text(shown.text(s), style = MaterialTheme.typography.titleMedium, color = TextHigh, modifier = Modifier.weight(1f))
                    val action = shown.action
                    val label = shown.actionLabel
                    if (action != null && label != null) {
                        Text(
                            label(s).uppercase(), style = MaterialTheme.typography.labelMedium, color = tone.color(),
                            modifier = Modifier.clip(RoundedCornerShape(50)).pressable { action(); state.dismiss(shown.id) }.padding(horizontal = 12.dp, vertical = 10.dp),
                        )
                    }
                }
                // shrinking life-line
                Box(Modifier.fillMaxWidth(life.value).height(3.dp).background(tone.color().copy(alpha = 0.7f)))
            }
        }
    }
}

// ───────────────────────────── Dialog ─────────────────────────────

/**
 * Velvet confirm dialog: scrim fades in, card springs up with a bouncing icon badge.
 * Keep it in the tree and drive it with [visible] so it can animate out.
 */
@Composable
fun SpinzyDialog(
    visible: Boolean,
    icon: ImageVector,
    title: String,
    body: String,
    confirm: String,
    dismiss: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    destructive: Boolean = false,
) {
    Box(Modifier.fillMaxSize()) {
        AnimatedVisibility(visible, enter = fadeIn(tween(200)), exit = fadeOut(tween(200))) {
            Box(
                Modifier.fillMaxSize().background(Color(0xCC12030E))
                    .clickable(remember { MutableInteractionSource() }, null, onClick = onDismiss),
            )
        }
        AnimatedVisibility(
            visible, modifier = Modifier.align(Alignment.Center).padding(28.dp),
            enter = scaleIn(spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow), initialScale = 0.8f) + fadeIn(),
            exit = scaleOut(tween(160), targetScale = 0.9f) + fadeOut(tween(160)),
        ) {
            val badge = remember { Animatable(0f) }
            LaunchedEffect(Unit) { delay(80); badge.animateTo(1f, spring(dampingRatio = 0.35f, stiffness = Spring.StiffnessLow)) }
            Column(
                Modifier.widthIn(max = 420.dp).fillMaxWidth()
                    .shadow(40.dp, Squircle, ambientColor = Coral, spotColor = Coral)
                    .clip(SquircleShape(5.0))
                    .background(Brush.linearGradient(listOf(Wine, MidnightRaised)))
                    .border(1.dp, Cream.copy(alpha = 0.1f), SquircleShape(5.0))
                    .clickable(remember { MutableInteractionSource() }, null) {}
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                IconBadge(icon, Modifier.graphicsLayer { scaleX = badge.value; scaleY = badge.value; rotationZ = (1 - badge.value) * 40f }, size = 64.dp, bg = if (destructive) Coral else Peach, tint = Wine)
                Spacer(Modifier.height(18.dp))
                Text(title, style = MaterialTheme.typography.headlineMedium, color = TextHigh, textAlign = TextAlign.Center)
                Spacer(Modifier.height(8.dp))
                Text(body, style = MaterialTheme.typography.bodyLarge, color = TextMid, textAlign = TextAlign.Center)
                Spacer(Modifier.height(24.dp))
                GlowButton(confirm, colors = if (destructive) listOf(Coral, CoralDeep) else listOf(Peach, Coral), textColor = Wine, modifier = Modifier.fillMaxWidth(), height = 54.dp, onClick = onConfirm)
                Spacer(Modifier.height(6.dp))
                Text(dismiss, style = MaterialTheme.typography.labelLarge, color = TextLow, modifier = Modifier.clip(RoundedCornerShape(50)).pressable(onClick = onDismiss).padding(horizontal = 20.dp, vertical = 12.dp))
            }
        }
    }
}

// ───────────────────────────── Rating ─────────────────────────────

@Composable
fun RatingDialog(visible: Boolean, onRate: (Int) -> Unit, onDismiss: () -> Unit) {
    val s = LocalStrings.current
    var stars by remember { mutableIntStateOf(0) }
    Box(Modifier.fillMaxSize()) {
        AnimatedVisibility(visible, enter = fadeIn(), exit = fadeOut()) {
            Box(Modifier.fillMaxSize().background(Color(0xCC12030E)).clickable(remember { MutableInteractionSource() }, null, onClick = onDismiss))
        }
        AnimatedVisibility(
            visible, modifier = Modifier.align(Alignment.Center).padding(28.dp),
            enter = scaleIn(spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow), initialScale = 0.8f) + fadeIn(),
            exit = scaleOut(tween(160), targetScale = 0.9f) + fadeOut(tween(160)),
        ) {
            Column(
                Modifier.widthIn(max = 420.dp).fillMaxWidth().clip(SquircleShape(5.0))
                    .background(Brush.linearGradient(listOf(Wine, MidnightRaised))).padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(s.rateTitle, style = MaterialTheme.typography.headlineMedium, color = TextHigh, textAlign = TextAlign.Center)
                Spacer(Modifier.height(6.dp))
                Text(s.rateBody, style = MaterialTheme.typography.bodyLarge, color = TextMid, textAlign = TextAlign.Center)
                Spacer(Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    for (i in 1..5) {
                        val a = remember { Animatable(0f) }
                        LaunchedEffect(Unit) { delay(90L * i); a.animateTo(1f, spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMediumLow)) }
                        val filled = i <= stars
                        val bump = remember(filled) { Animatable(if (filled) 1.4f else 1f) }
                        LaunchedEffect(filled) { bump.animateTo(1f, spring(dampingRatio = 0.3f)) }
                        Icon(
                            if (filled) Icons.Rounded.Star else Icons.Rounded.StarBorder, "$i",
                            tint = if (filled) Peach else TextLow,
                            modifier = Modifier.size(44.dp)
                                .graphicsLayer { val sc = a.value * bump.value; scaleX = sc; scaleY = sc; alpha = a.value.coerceIn(0f, 1f) }
                                .pressable(pressedScale = 0.8f) { stars = i },
                        )
                    }
                }
                Spacer(Modifier.height(22.dp))
                GlowButton(s.continueLabel, colors = listOf(Peach, Coral), textColor = Wine, enabled = stars > 0, height = 54.dp, modifier = Modifier.fillMaxWidth()) { onRate(stars) }
                Spacer(Modifier.height(6.dp))
                Text(s.notNow, style = MaterialTheme.typography.labelLarge, color = TextLow, modifier = Modifier.clip(RoundedCornerShape(50)).pressable(onClick = onDismiss).padding(horizontal = 20.dp, vertical = 12.dp))
            }
        }
    }
}

// ───────────────────────────── Shake ─────────────────────────────

/** Horizontal "no!" shake whenever [trigger] changes (validation feedback). */
@Composable
fun Modifier.shakeOn(trigger: Int): Modifier {
    val x = remember { Animatable(0f) }
    val px = with(LocalDensity.current) { 10.dp.toPx() }
    LaunchedEffect(trigger) {
        if (trigger == 0) return@LaunchedEffect
        for (v in listOf(1f, -1f, 0.7f, -0.7f, 0.35f, -0.35f, 0f)) x.animateTo(v, tween(45))
    }
    return graphicsLayer { translationX = x.value * px }
}
