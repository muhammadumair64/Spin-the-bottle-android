package com.umair.spinzy.ui.screens

import android.view.HapticFeedbackConstants
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.Casino
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Flag
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker1D
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umair.spinzy.audio.LocalSfx
import com.umair.spinzy.audio.Sfx
import com.umair.spinzy.data.Player
import com.umair.spinzy.data.PopKind
import com.umair.spinzy.data.PromptType
import com.umair.spinzy.data.ScorePop
import com.umair.spinzy.data.WildCard
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.game.Phase
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.ui.components.AppIcons
import com.umair.spinzy.ui.components.GlassCard
import com.umair.spinzy.ui.components.GlassIconButton
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.IconBadge
import com.umair.spinzy.ui.components.NeonBottle
import com.umair.spinzy.ui.components.OnShake
import com.umair.spinzy.ui.components.PlayerBubble
import com.umair.spinzy.ui.components.SpinzyDialog
import com.umair.spinzy.ui.components.SpinzyTopBar
import com.umair.spinzy.ui.components.Squircle
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.theme.Blush
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.CoralDeep
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.Ink
import com.umair.spinzy.ui.theme.Midnight
import com.umair.spinzy.ui.theme.MidnightRaised
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.PlayerColors
import com.umair.spinzy.ui.theme.Sunflower
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.random.Random

/** Fast start, long buttery deceleration — like a real bottle on a table. Initial slope ≈ 6.7. */
private val SpinEasing = CubicBezierEasing(0.12f, 0.8f, 0.2f, 1f)
private const val EASING_START_SLOPE = 6.67f
private const val SETTLE_OVERSHOOT = 5f

/**
 * Game v2 — one focal point (the bottle), one clear action (tap or swipe it),
 * a celebratory "it's you!" moment, then a bottom sheet to choose.
 */
@Composable
fun GameScreen(state: GameState, vm: GameViewModel) {
    val s = LocalStrings.current
    val players = state.players
    val n = players.size.coerceAtLeast(1)
    val step = 360f / n
    val rotation = remember { Animatable(0f) }
    var spinning by remember { mutableStateOf(false) }
    var showBoard by remember { mutableStateOf(false) }
    var touched by remember { mutableStateOf(state.turn > 0) }       // hides the gesture hint after the first spin
    var pointerAt by remember { mutableStateOf<Int?>(null) }
    var confirmEnd by remember { mutableStateOf(false) }
    var confirmLeave by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val view = LocalView.current
    val sfx = LocalSfx.current
    val settings by rememberUpdatedState(state.settings)
    val phase by rememberUpdatedState(state.phase)

    BackHandler(enabled = !confirmEnd && !confirmLeave) {
        when {
            showBoard -> showBoard = false
            state.turn > 0 -> confirmLeave = true
            else -> vm.back()
        }
    }

    fun buzz(strong: Boolean) {
        if (!settings.haptics) return
        view.performHapticFeedback(if (strong) HapticFeedbackConstants.LONG_PRESS else HapticFeedbackConstants.CLOCK_TICK)
    }

    val pointed by remember(n) { derivedStateOf { (((rotation.value.mod(360f)) + step / 2f) / step).toInt() % n } }
    LaunchedEffect(n) {
        snapshotFlow { pointed }.distinctUntilChanged().collect {
            buzz(false)
            sfx?.play(Sfx.TICK, volume = 0.6f, rate = 0.9f + Random.nextFloat() * 0.2f)
        }
    }

    fun spin(velocity: Float) {
        if (spinning || phase != Phase.SPIN || players.size < 2) return
        spinning = true; touched = true; pointerAt = null
        sfx?.play(Sfx.WHOOSH, volume = 0.8f)
        scope.launch {
            val k = vm.pickTarget()
            val dir = if (velocity >= 0f) 1f else -1f
            val speed = abs(velocity).coerceIn(900f, 4200f)
            val spins = (speed / 650f).coerceIn(2f, 7f).roundToInt()
            val cur = rotation.value
            val landing = k * step + (Random.nextFloat() - 0.5f) * step * 0.45f
            val diff = if (dir > 0) (landing - cur).mod(360f) else -((cur - landing).mod(360f))
            val target = cur + diff + dir * spins * 360f
            val duration = (abs(target - cur) * EASING_START_SLOPE / speed * 1000f).toInt().coerceIn(2400, 6500)
            rotation.animateTo(target + dir * SETTLE_OVERSHOOT, tween(duration, easing = SpinEasing))
            rotation.animateTo(target, spring(dampingRatio = 0.35f, stiffness = Spring.StiffnessLow))
            buzz(true)
            sfx?.play(Sfx.LAND)
            spinning = false
            pointerAt = k
            vm.onBottleLanded(k)
            sfx?.play(Sfx.REVEAL, 0.6f)
        }
    }
    OnShake(settings.shakeToSpin) { dir -> spin((2200f + Random.nextFloat() * 1800f) * dir) }

    val blurAmt by animateDpAsState(if (state.phase == Phase.PROMPT || showBoard) 14.dp else 0.dp, tween(350), label = "blur")
    val choosing = state.phase == Phase.CHOOSE

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize().blur(blurAmt).safeDrawingPadding()) {
            Box(Modifier.padding(horizontal = 20.dp)) { SpinzyTopBar(
                title = s.round(state.turn / n + 1),
                subtitle = if (settings.heatEscalation) s.heatLevel(state.heatCap) else s.turnsPlayed(state.turn),
                onBack = { if (state.turn > 0) confirmLeave = true else vm.back() },
            ) {
                ScoresButton(players) { showBoard = true }
                Spacer(Modifier.width(8.dp))
                GlassIconButton(onClick = { if (state.turn > 0) confirmEnd = true else vm.endGame() }) {
                    Icon(Icons.Rounded.Flag, contentDescription = s.endGame, tint = Sunflower)
                }
            } }

            // ---------------- Arena (the hero) ----------------
            BoxWithConstraints(Modifier.fillMaxWidth().weight(1f).padding(horizontal = 6.dp), contentAlignment = Alignment.Center) {
                val arena = minOf(maxWidth, maxHeight)
                // ── Responsive geometry: everything is derived from the arena so it fits any phone ──
                // players sit on the outer ring (with room for their names); the pad + bottle live
                // strictly inside the free circle left in the middle, so nothing ever overlaps.
                val bubble = (arena * (if (players.size > 8) 0.14f else 0.16f)).coerceIn(42.dp, 62.dp)
                val labelSpace = 22.dp
                val radius = arena / 2 - bubble / 2 - labelSpace
                val inner = (radius - bubble / 2 - 10.dp).coerceAtLeast(40.dp)   // free radius in the middle
                val bottleH = inner * 2 * 0.88f
                val bottleW = bottleH / 2.9f
                val density = LocalDensity.current
                val tracker = remember { VelocityTracker1D(isDataDifferential = true) }
                val beam by animateFloatAsState(if (state.phase != Phase.SPIN && state.selected != null) 1f else 0f, tween(450), label = "beam")
                val arenaDim by animateFloatAsState(if (choosing) 0.55f else 1f, tween(400), label = "dim")

                Box(
                    Modifier.size(arena)
                        .graphicsLayer {
                            // while the chooser sheet is open, shrink the arena and lift it so the picked player stays visible
                            val t = (1f - arenaDim) / 0.45f
                            val k = 1f - 0.24f * t
                            scaleX = k; scaleY = k
                            translationY = -arena.toPx() * 0.22f * t
                        }
                        .pointerInput(n) {
                            detectTapGestures { if (phase == Phase.SPIN) spin(1600f + Random.nextFloat() * 1500f) }
                        }
                        .pointerInput(n) {
                            val c = Offset(size.width / 2f, size.height / 2f)
                            var lastAngle = 0f
                            detectDragGestures(
                                onDragStart = { pos -> tracker.resetTracking(); lastAngle = angleOf(pos - c); touched = true },
                                onDrag = { change, _ ->
                                    if (spinning || phase != Phase.SPIN) return@detectDragGestures
                                    val a = angleOf(change.position - c)
                                    var d = a - lastAngle
                                    if (d > 180f) d -= 360f
                                    if (d < -180f) d += 360f
                                    lastAngle = a
                                    tracker.addDataPoint(change.uptimeMillis, d)
                                    scope.launch { rotation.snapTo(rotation.value + d) }
                                },
                                onDragEnd = { val v = tracker.calculateVelocity(); if (abs(v) > 240f) spin(v) },
                            )
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    val sel = state.selected
                    if (sel != null && sel < players.size) {
                        val color = PlayerColors[players[sel].colorIndex % PlayerColors.size]
                        Canvas(Modifier.fillMaxSize().graphicsLayer { alpha = beam }) {
                            val ang = (-90f + sel * step) * PI.toFloat() / 180f
                            val r = with(density) { radius.toPx() }
                            val tip = Offset(center.x + r * cos(ang), center.y + r * sin(ang))
                            val spread = with(density) { bubble.toPx() } * 0.75f
                            val px = -sin(ang) * spread; val py = cos(ang) * spread
                            val path = Path().apply { moveTo(center.x, center.y); lineTo(tip.x + px, tip.y + py); lineTo(tip.x - px, tip.y - py); close() }
                            drawPath(path, Brush.radialGradient(listOf(color.copy(alpha = 0.55f), color.copy(alpha = 0.02f)), center = center, radius = r * 1.1f))
                        }
                    }

                    SpinPad(Modifier.size(inner * 2), spinning)

                    players.forEachIndexed { i, p ->
                        val a = (-90f + i * step) * (PI / 180f).toFloat()
                        val off = with(density) { IntOffset((radius.toPx() * cos(a)).roundToInt(), (radius.toPx() * sin(a)).roundToInt()) }
                        val top = sin(a) < -0.2f
                        PlayerBubble(
                            p.avatar, p.name, PlayerColors[p.colorIndex % PlayerColors.size],
                            Modifier.absoluteOffset { off }.offset(y = if (top) (-12).dp else 12.dp),
                            size = bubble,
                            highlighted = when { state.phase != Phase.SPIN -> state.selected == i; spinning -> pointed == i; else -> false },
                            dimmed = state.phase != Phase.SPIN && state.selected != i && state.mirrorWith != i,
                            labelOnTop = top,
                            streak = p.streak,
                        )
                    }

                    // idle breathing makes the bottle feel "touchable"
                    val idle = rememberInfiniteTransition(label = "idle")
                    val breathe by idle.animateFloat(1f, 1.05f, infiniteRepeatable(tween(1100), RepeatMode.Reverse), label = "b")
                    NeonBottle(
                        Modifier.size(bottleW, bottleH).graphicsLayer {
                            rotationZ = rotation.value
                            val k = if (!spinning && state.phase == Phase.SPIN) breathe else 1f
                            scaleX = k; scaleY = k
                        },
                        glow = if (spinning) 1.6f else 1f, skin = settings.skin,
                    )

                    val hintAlpha by animateFloatAsState(if (!touched && state.phase == Phase.SPIN && players.size >= 2) 1f else 0f, tween(500), label = "hint")
                    if (hintAlpha > 0f) GestureHint(Modifier.size(inner * 1.4f).graphicsLayer { alpha = hintAlpha })

                    PickBurst(state, Modifier.align(Alignment.Center))
                    ScorePopLayer(state.pop)
                }
            }

            // ---------------- one clear action ----------------
            AnimatedVisibility(
                !choosing,
                enter = slideInVertically(spring(dampingRatio = 0.8f)) { it / 2 } + fadeIn(),
                exit = slideOutVertically(tween(200)) { it / 2 } + fadeOut(tween(150)),
            ) {
                Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        if (spinning) s.spinning else s.tapOrSwipeBottle,
                        style = MaterialTheme.typography.bodyLarge, color = TextMid, textAlign = TextAlign.Center,
                    )
                    Spacer(Modifier.height(10.dp))
                    GlowButton(
                        text = s.spinTheBottle, icon = Icons.Rounded.Autorenew,
                        colors = listOf(Coral, CoralDeep),
                        enabled = !spinning && state.phase == Phase.SPIN,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { spin(1400f + Random.nextFloat() * 1600f) },
                    )
                }
            }
        }

        // ---------------- choose: bottom sheet ----------------
        AnimatedVisibility(
            choosing, modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically(spring(dampingRatio = 0.78f, stiffness = Spring.StiffnessMediumLow)) { it } + fadeIn(),
            exit = slideOutVertically(tween(220)) { it } + fadeOut(tween(180)),
        ) {
            ChooserSheet(state, pointerAt) { t -> sfx?.play(Sfx.REVEAL); vm.choose(t) }
        }

        // ---------------- prompt card ----------------
        AnimatedVisibility(
            visible = state.phase == Phase.PROMPT && state.prompt != null,
            enter = fadeIn(tween(250)), exit = fadeOut(tween(250)),
        ) {
            val prompt = state.prompt
            val player = state.selectedPlayer
            if (prompt != null && player != null) {
                PromptOverlay(
                    prompt = prompt, player = player, dareSeconds = settings.dareSeconds, wild = state.wild,
                    partner = state.mirrorWith?.let { players.getOrNull(it) },
                    onDone = { sfx?.play(Sfx.POP); vm.complete() },
                    onBusted = { sfx?.play(Sfx.POP, rate = 0.7f); vm.busted() },
                    onSkip = { sfx?.play(Sfx.REVEAL, volume = 0.6f); vm.skip() },
                    onChicken = { sfx?.play(Sfx.POP, rate = 0.6f); vm.chickenOut() },
                    onTick = { buzz(false); sfx?.play(Sfx.TICK) },
                )
            }
        }

        ScoreboardSheet(visible = showBoard, players = players, onDismiss = { showBoard = false })

        SpinzyDialog(
            visible = confirmEnd, icon = Icons.Rounded.EmojiEvents,
            title = s.endGameTitle, body = s.endGameBody, confirm = s.endGameConfirm, dismiss = s.keepPlaying,
            onConfirm = { confirmEnd = false; vm.endGame() }, onDismiss = { confirmEnd = false },
        )
        SpinzyDialog(
            visible = confirmLeave, icon = Icons.AutoMirrored.Rounded.ExitToApp, destructive = true,
            title = s.leaveGameTitle, body = s.leaveGameBody, confirm = s.leave, dismiss = s.keepPlaying,
            onConfirm = { confirmLeave = false; vm.back() }, onDismiss = { confirmLeave = false },
        )
    }
}

/** Overlapping avatars of the top 3 with a crown on the leader — opens the leaderboard. */
@Composable
private fun ScoresButton(players: List<Player>, onClick: () -> Unit) {
    val top = players.sortedByDescending { it.score }.take(3)
    GlassCard(Modifier.height(48.dp).pressable(onClick = onClick), shape = RoundedCornerShape(50)) {
        Row(Modifier.padding(start = 8.dp, end = 12.dp).align(Alignment.Center), verticalAlignment = Alignment.CenterVertically) {
            // negative spacing = real overlap (offset() would leave empty space behind)
            Row(horizontalArrangement = Arrangement.spacedBy((-10).dp)) {
                top.forEach { p ->
                    val c = PlayerColors[p.colorIndex % PlayerColors.size]
                    Box(
                        Modifier.size(28.dp).clip(CircleShape).background(c).border(2.dp, MidnightRaised, CircleShape),
                        contentAlignment = Alignment.Center,
                    ) { Icon(AppIcons.avatar(p.avatar), null, tint = Wine, modifier = Modifier.size(15.dp)) }
                }
            }
            val lead = top.firstOrNull()
            if (lead != null) {
                Spacer(Modifier.width(6.dp))
                Text("${lead.score}", style = MaterialTheme.typography.labelLarge, color = Sunflower)
            }
        }
    }
}

/** Circling finger + dashed arc: "you can flick this". Shown until the first spin. */
@Composable
private fun GestureHint(modifier: Modifier) {
    val t = rememberInfiniteTransition(label = "hint")
    val a by t.animateFloat(-45f, 45f, infiniteRepeatable(tween(1300), RepeatMode.Reverse), label = "a")
    Box(modifier) {
        Canvas(Modifier.fillMaxSize()) {
            val r = size.minDimension / 2f
            drawArc(
                Cream.copy(alpha = 0.35f), -135f, 90f, false,
                topLeft = Offset(center.x - r, center.y - r), size = androidx.compose.ui.geometry.Size(r * 2, r * 2),
                style = Stroke(5f, cap = StrokeCap.Round, pathEffect = PathEffect.dashPathEffect(floatArrayOf(14f, 14f))),
            )
        }
        Box(Modifier.fillMaxSize().graphicsLayer { rotationZ = a }, contentAlignment = Alignment.TopCenter) {
            IconBadge(Icons.Rounded.TouchApp, Modifier.offset(y = (-18).dp), size = 40.dp, bg = Peach, tint = Wine)
        }
    }
}

/** "It's you!" — the picked player's avatar and name burst in the middle, then fade. */
@Composable
private fun PickBurst(state: GameState, modifier: Modifier) {
    val p = state.selectedPlayer
    val key = if (state.phase == Phase.CHOOSE && p != null) "${state.turn}-${p.id}" else null
    val anim = remember { Animatable(0f) }
    var shown by remember { mutableStateOf<Player?>(null) }
    LaunchedEffect(key) {
        if (key == null) { anim.snapTo(0f); return@LaunchedEffect }
        shown = p
        anim.snapTo(0f)
        anim.animateTo(1f, spring(dampingRatio = 0.45f, stiffness = Spring.StiffnessMediumLow))
        delay(650)
        anim.animateTo(0f, tween(260))
    }
    val who = shown ?: return
    val color = PlayerColors[who.colorIndex % PlayerColors.size]
    Column(
        modifier.graphicsLayer { val k = 0.4f + 0.6f * anim.value; scaleX = k; scaleY = k; alpha = anim.value.coerceIn(0f, 1f) }
            .shadow(30.dp, Squircle, ambientColor = color, spotColor = color)
            .clip(Squircle).background(Brush.linearGradient(listOf(color, color.copy(alpha = 0.8f))))
            .padding(horizontal = 26.dp, vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(AppIcons.avatar(who.avatar), null, tint = Wine, modifier = Modifier.size(40.dp))
        BasicText(
            who.name, style = MaterialTheme.typography.displaySmall.copy(color = Wine), maxLines = 1,
            autoSize = TextAutoSize.StepBased(minFontSize = 18.sp, maxFontSize = 34.sp),
        )
    }
}

private fun angleOf(v: Offset): Float = (atan2(v.y, v.x) * 180f / PI.toFloat())

/** "+3" pill that floats up from the bottle and fades away. */
@Composable
private fun ScorePopLayer(pop: ScorePop?) {
    if (pop == null) return
    val s = LocalStrings.current
    val text = when (pop.kind) {
        PopKind.POINTS -> buildString {
            append("+${pop.points}")
            if (pop.streak) append("  ${s.streakBonus}")
            if (pop.everyone) append("  ${s.forEveryone}")
        }
        PopKind.BUSTED -> s.busted
        PopKind.CHICKEN -> "−1  ${s.chicken}"
    }
    val anim = remember(pop.id) { Animatable(0f) }
    LaunchedEffect(pop.id) { anim.animateTo(1f, tween(1700, easing = LinearEasing)) }
    Text(
        text,
        style = MaterialTheme.typography.headlineLarge,
        color = Midnight,
        modifier = Modifier
            .graphicsLayer {
                val p = anim.value
                // pop in with overshoot, hang, then drift up & fade
                val inP = (p / 0.18f).coerceAtMost(1f)
                val s = if (p < 0.18f) 0.4f + 0.8f * inP else 1.2f - 0.2f * ((p - 0.18f) / 0.1f).coerceAtMost(1f)
                scaleX = s; scaleY = s
                translationY = -size.height * 1.2f - 260f * ((p - 0.5f).coerceAtLeast(0f) / 0.5f)
                alpha = if (p < 0.6f) inP else (1f - p) / 0.4f
                shadowElevation = 24f
                shape = RoundedCornerShape(50)
                clip = true
                ambientShadowColor = pop.color; spotShadowColor = pop.color
            }
            .background(Brush.horizontalGradient(listOf(pop.color, pop.color.copy(alpha = 0.8f))))
            .padding(horizontal = 20.dp, vertical = 8.dp),
    )
}

/** Glass disc with a slowly rotating ring of tick marks under the bottle. */
@Composable
private fun SpinPad(modifier: Modifier, spinning: Boolean) {
    val t = rememberInfiniteTransition(label = "pad")
    val r by t.animateFloat(0f, 360f, infiniteRepeatable(tween(40_000, easing = LinearEasing)), label = "r")
    GlassCard(modifier, shape = RoundedCornerShape(50)) {
        Canvas(Modifier.fillMaxSize()) {
            rotate(-r) {
                val ticks = 48
                for (i in 0 until ticks) {
                    val a = (i * 360f / ticks) * (PI / 180f).toFloat()
                    val outer = size.minDimension / 2f - 10f
                    val inner = outer - if (i % 4 == 0) 22f else 10f
                    drawLine(
                        (if (spinning) Sunflower else Color.White).copy(alpha = if (i % 4 == 0) 0.45f else 0.18f),
                        Offset(center.x + inner * cos(a), center.y + inner * sin(a)),
                        Offset(center.x + outer * cos(a), center.y + outer * sin(a)),
                        strokeWidth = 3f,
                    )
                }
            }
        }
    }
}

@Composable
private fun ChooserSheet(state: GameState, pointerAt: Int?, onPick: (PromptType?) -> Unit) {
    val s = LocalStrings.current
    val p = state.selectedPlayer ?: return
    val wild = state.wild
    val color = PlayerColors[p.colorIndex % PlayerColors.size]
    Column(
        Modifier.fillMaxWidth()
            .shadow(40.dp, RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp), ambientColor = color, spotColor = color)
            .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
            .background(Brush.verticalGradient(listOf(Ink, MidnightRaised)))
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(Modifier.width(44.dp).height(5.dp).clip(RoundedCornerShape(50)).background(TextLow.copy(alpha = 0.6f)))
        Spacer(Modifier.height(14.dp))
        if (wild != null) {
            WildBanner(wild, state, pointerAt)
            Spacer(Modifier.height(14.dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            PlayerBubble(p.avatar, null, color, size = 44.dp)
            Spacer(Modifier.width(12.dp))
            Column {
                Text(p.name, style = MaterialTheme.typography.headlineMedium, color = TextHigh, maxLines = 1)
                Text(s.chooserQuestion, style = MaterialTheme.typography.bodyLarge, color = TextMid)
            }
        }
        Spacer(Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            val mult = if (wild == WildCard.DOUBLE) 2 else 1
            ChoiceCard(s.truth, s.points(1 * mult), listOf(PeachLight, Blush), Modifier.weight(1f).graphicsLayer { rotationZ = -3f }) { onPick(PromptType.TRUTH) }
            ChoiceCard(s.dare, s.points(2 * mult), listOf(Peach, Coral), Modifier.weight(1f).graphicsLayer { rotationZ = 3f }) { onPick(PromptType.DARE) }
        }
        Spacer(Modifier.height(6.dp))
        Row(Modifier.clip(RoundedCornerShape(50)).pressable { onPick(null) }.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Rounded.Casino, null, tint = Sunflower, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text(s.letFateDecide, style = MaterialTheme.typography.labelLarge, color = Sunflower)
        }
    }
}

/** Wild card banner — pops in with a springy tilt. */
@Composable
private fun WildBanner(wild: WildCard, state: GameState, pointerAt: Int?) {
    val s = LocalStrings.current
    val a = remember(wild) { Animatable(0f) }
    LaunchedEffect(wild) { a.animateTo(1f, spring(dampingRatio = 0.45f, stiffness = Spring.StiffnessLow)) }
    val shimmer = rememberInfiniteTransition(label = "wild")
    val glow by shimmer.animateFloat(0.4f, 1f, infiniteRepeatable(tween(700), RepeatMode.Reverse), label = "g")
    val detail = when (wild) {
        WildCard.HOT_POTATO -> pointerAt?.let { s.passedFromTo(state.players[it].name, state.selectedPlayer?.name ?: "") }
        WildCard.MIRROR -> state.mirrorWith?.let { s.joins(state.players[it].name, state.selectedPlayer?.name ?: "") }
        else -> null
    }
    Row(
        Modifier.fillMaxWidth()
            .graphicsLayer { scaleX = a.value; scaleY = a.value; rotationZ = (1f - a.value) * -12f; alpha = a.value.coerceIn(0f, 1f) }
            .background(Brush.horizontalGradient(listOf(wild.color.copy(alpha = 0.35f + 0.25f * glow), wild.color.copy(alpha = 0.1f))), RoundedCornerShape(22.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconBadge(AppIcons.wild(wild), size = 40.dp, bg = wild.color, tint = Wine)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text("${s.wildCardCaps} · ${s.wildTitle(wild).uppercase()}", style = MaterialTheme.typography.labelMedium, color = wild.color)
            Text(detail ?: s.wildBody(wild), style = MaterialTheme.typography.bodyMedium, color = TextHigh)
        }
    }
}

@Composable
private fun ChoiceCard(title: String, sub: String, colors: List<Color>, modifier: Modifier, onClick: () -> Unit) {
    val t = rememberInfiniteTransition(label = "choice")
    val glow by t.animateFloat(0.6f, 1f, infiniteRepeatable(tween(1200), RepeatMode.Reverse), label = "g")
    Box(
        modifier.height(118.dp).pressable(pressedScale = 0.9f, onClick = onClick)
            .graphicsLayer { shadowElevation = 30f * glow; shape = Squircle; clip = true; ambientShadowColor = colors[0]; spotShadowColor = colors[0] },
        contentAlignment = Alignment.Center,
    ) {
        Canvas(Modifier.fillMaxSize()) {
            drawRect(Brush.linearGradient(colors))
            drawCircle(Color.White.copy(alpha = 0.18f * glow), radius = size.minDimension * 0.9f, center = Offset(size.width * 0.9f, 0f))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BasicText(
                title, style = MaterialTheme.typography.displaySmall.copy(color = Wine), maxLines = 1,
                autoSize = TextAutoSize.StepBased(minFontSize = 16.sp, maxFontSize = 32.sp), modifier = Modifier.padding(horizontal = 10.dp),
            )
            Text(sub, style = MaterialTheme.typography.labelLarge, color = Wine.copy(alpha = 0.7f))
        }
    }
}
