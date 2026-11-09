package com.umair.spinzy.ui.screens

import com.umair.spinzy.i18n.LocalStrings
import android.view.HapticFeedbackConstants
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Toll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.umair.spinzy.audio.LocalSfx
import com.umair.spinzy.audio.Sfx
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.ui.components.GlassCard
import com.umair.spinzy.ui.components.LocalToaster
import com.umair.spinzy.game.ToastTone
import com.umair.spinzy.ui.components.GlassTextField
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.OnShake
import com.umair.spinzy.ui.components.SpinzyTopBar
import com.umair.spinzy.ui.components.enterUp
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.theme.Apricot
import com.umair.spinzy.ui.theme.Blush
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.CoralDeep
import com.umair.spinzy.ui.theme.Lilac
import com.umair.spinzy.ui.theme.Mauve
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

private val SideA = listOf(PeachLight, Peach, Apricot)
private val SideB = listOf(Blush, Mauve, Lilac)

@Composable
fun CoinScreen(state: GameState, vm: GameViewModel) {
    val str = LocalStrings.current
    val toaster = LocalToaster.current
    val s = state.settings.let { it.copy(coinA = it.coinA.ifBlank { str.heads }, coinB = it.coinB.ifBlank { str.tails }) }
    val rot = remember { Animatable(0f) }     // rotationX in degrees; 0 = side A up
    val toss = remember { Animatable(0f) }    // 0..1 arc progress
    val land = remember { Animatable(1f) }    // squash on landing
    var flipping by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf<Boolean?>(null) } // true = A
    val history = remember { mutableStateListOf<Boolean>() }
    var countA by remember { mutableIntStateOf(0) }
    var countB by remember { mutableIntStateOf(0) }
    var editing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sfx = LocalSfx.current
    val view = LocalView.current

    fun flip() {
        if (flipping) return
        flipping = true
        result = null
        sfx?.play(Sfx.WHOOSH, 0.7f, 1.3f)
        scope.launch {
            val isA = Random.nextBoolean()
            val half = 180f
            val cur = rot.value
            val curIsA = (cur / half).toInt() % 2 == 0
            val turns = 8 + Random.nextInt(3) * 2 // even number of half turns keeps side…
            val extra = if (curIsA == isA) 0 else 1 // …plus one to switch
            val target = cur + (turns + extra) * half
            launch { toss.snapTo(0f); toss.animateTo(1f, tween(1500, easing = LinearEasing)) }
            rot.animateTo(target, tween(1500, easing = FastOutSlowInEasing))
            // landing squash + bounce
            land.snapTo(0.82f)
            sfx?.play(Sfx.LAND, 0.8f, 1.4f)
            if (s.haptics) view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            land.animateTo(1f, spring(dampingRatio = 0.35f, stiffness = Spring.StiffnessMedium))
            result = isA
            if (isA) countA++ else countB++
            history.add(0, isA); if (history.size > 12) history.removeAt(history.lastIndex)
            sfx?.play(Sfx.REVEAL, 0.6f)
            flipping = false
        }
    }
    OnShake(s.shakeToSpin) { flip() }

    Column(Modifier.fillMaxSize().safeDrawingPadding().verticalScroll(rememberScrollState()).padding(horizontal = 20.dp)) {
        SpinzyTopBar(str.coinFlip, str.coinHint, onBack = { vm.back() })
        Spacer(Modifier.height(10.dp))

        // ---------- Coin stage ----------
        Box(Modifier.fillMaxWidth().height(330.dp), contentAlignment = Alignment.BottomCenter) {
            // shadow grows smaller as the coin rises
            Canvas(Modifier.padding(bottom = 26.dp).size(170.dp, 26.dp)) {
                val h = sin(PI * toss.value).toFloat()
                val w = size.width * (1f - 0.5f * h)
                drawOval(Color.Black.copy(alpha = 0.35f * (1f - 0.6f * h)), topLeft = Offset((size.width - w) / 2, 0f), size = Size(w, size.height))
            }
            Box(
                Modifier.padding(bottom = 40.dp).size(190.dp)
                    .graphicsLayer {
                        val h = sin(PI * toss.value).toFloat()
                        translationY = -h * 120.dp.toPx()
                        val sc = (1f + 0.22f * h) * land.value
                        scaleX = sc * (2f - land.value); scaleY = sc
                    }
                    .pressable(pressedScale = 0.95f) { flip() },
                contentAlignment = Alignment.Center,
            ) {
                val showA = cos(rot.value * PI / 180.0) >= 0
                Box(
                    Modifier.fillMaxSize().graphicsLayer {
                        rotationX = rot.value
                        cameraDistance = 18f * density
                    },
                    contentAlignment = Alignment.Center,
                ) {
                    // Back face content is counter-rotated so the text never appears mirrored
                    Box(Modifier.fillMaxSize().graphicsLayer { rotationX = if (showA) 0f else 180f }) {
                        CoinFace(if (showA) s.coinA else s.coinB, if (showA) SideA else SideB)
                    }
                }
            }
        }

        // ---------- Result ----------
        AnimatedContent(
            targetState = result,
            transitionSpec = { (fadeIn(tween(250)) + scaleIn(spring(dampingRatio = 0.45f), initialScale = 0.6f)) togetherWith fadeOut(tween(120)) },
            label = "result", modifier = Modifier.fillMaxWidth(),
        ) { r ->
            Text(
                when (r) { null -> if (flipping) str.inTheAir else str.callIt; true -> s.coinA; false -> s.coinB },
                style = MaterialTheme.typography.displaySmall,
                color = when (r) { true -> Peach; false -> Blush; null -> TextMid },
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),
                maxLines = 1, overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(Modifier.height(14.dp))
        GlowButton(str.flipTheCoin, colors = listOf(Coral, CoralDeep), enabled = !flipping, icon = Icons.Rounded.Toll, modifier = Modifier.fillMaxWidth()) { flip() }

        // ---------- Tally ----------
        Spacer(Modifier.height(18.dp))
        Tally(s.coinA, s.coinB, countA, countB, Modifier.enterUp(80))
        if (history.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(history) { i, a ->
                    Box(
                        Modifier.size(30.dp).graphicsLayer { alpha = 1f - i * 0.06f }.clip(CircleShape)
                            .background(Brush.linearGradient(if (a) SideA else SideB)),
                        contentAlignment = Alignment.Center,
                    ) { Text(if (a) s.coinA.take(1).uppercase() else s.coinB.take(1).uppercase(), style = MaterialTheme.typography.labelLarge, color = Wine) }
                }
            }
        }

        // ---------- Customise sides ----------
        Spacer(Modifier.height(18.dp))
        GlassCard(Modifier.fillMaxWidth().enterUp(140), shape = RoundedCornerShape(26.dp)) {
            Column(Modifier.padding(18.dp)) {
                Row(Modifier.fillMaxWidth().pressable { if (editing) toaster.show(ToastTone.SUCCESS) { it.coinSaved }; editing = !editing }, verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.Edit, null, tint = Peach, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text(str.ownTwoOptions, style = MaterialTheme.typography.titleMedium, color = TextHigh)
                        Text(str.ownTwoOptionsSub, style = MaterialTheme.typography.bodyMedium, color = TextMid)
                    }
                    Text(if (editing) str.done else str.edit, style = MaterialTheme.typography.labelLarge, color = Peach)
                }
                if (editing) {
                    Spacer(Modifier.height(14.dp))
                    SideField(str.sideOne, state.settings.coinA, SideA) { vm.updateSettings(state.settings.copy(coinA = it)) }
                    Spacer(Modifier.height(10.dp))
                    SideField(str.sideTwo, state.settings.coinB, SideB) { vm.updateSettings(state.settings.copy(coinB = it)) }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        str.resetHeadsTails, style = MaterialTheme.typography.labelLarge, color = TextLow,
                        modifier = Modifier.pressable { vm.updateSettings(state.settings.copy(coinA = "", coinB = "")); toaster.show(ToastTone.INFO) { it.coinSaved } }.padding(vertical = 6.dp),
                    )
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun SideField(label: String, value: String, colors: List<Color>, onChange: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(14.dp).clip(CircleShape).background(Brush.linearGradient(colors)))
        Spacer(Modifier.width(10.dp))
        GlassTextField(value, { onChange(it.take(18)) }, label, Modifier.weight(1f))
    }
}

@Composable
private fun Tally(a: String, b: String, ca: Int, cb: Int, modifier: Modifier) {
    val total = (ca + cb).coerceAtLeast(1)
    val frac by animateFloatAsState(if (ca + cb == 0) 0.5f else ca / total.toFloat(), spring(dampingRatio = 0.7f), label = "tally")
    Column(modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            Text("$a  $ca", style = MaterialTheme.typography.labelLarge, color = Peach, modifier = Modifier.weight(1f), maxLines = 1)
            Text("$cb  $b", style = MaterialTheme.typography.labelLarge, color = Blush, maxLines = 1)
        }
        Spacer(Modifier.height(6.dp))
        Row(Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(50))) {
            Box(Modifier.weight(frac.coerceIn(0.02f, 0.98f)).fillMaxSize().background(Brush.horizontalGradient(SideA)))
            Box(Modifier.weight((1f - frac).coerceIn(0.02f, 0.98f)).fillMaxSize().background(Brush.horizontalGradient(SideB)))
        }
    }
}

/** One face of the coin: gradient disc, milled rim, inner ring and the label. */
@Composable
private fun CoinFace(label: String, colors: List<Color>) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val r = size.minDimension / 2
            drawCircle(Brush.linearGradient(colors, start = Offset.Zero, end = Offset(size.width, size.height)), r)
            // milled edge
            for (i in 0 until 72) {
                val a = i * 5f * PI.toFloat() / 180f
                drawLine(
                    Wine.copy(alpha = 0.18f),
                    Offset(center.x + (r - 3) * cos(a), center.y + (r - 3) * sin(a)),
                    Offset(center.x + (r - 13) * cos(a), center.y + (r - 13) * sin(a)),
                    strokeWidth = 3f,
                )
            }
            drawCircle(Wine.copy(alpha = 0.25f), r * 0.78f, style = Stroke(4f))
            drawCircle(Color.White.copy(alpha = 0.35f), r * 0.98f, style = Stroke(3f))
            // glossy highlight
            drawCircle(
                Brush.radialGradient(listOf(Color.White.copy(alpha = 0.45f), Color.Transparent), center = Offset(r * 0.6f, r * 0.55f), radius = r * 0.8f),
                r * 0.95f,
            )
        }
        Text(
            label, style = MaterialTheme.typography.headlineMedium, color = Wine, textAlign = TextAlign.Center,
            maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(horizontal = 34.dp),
        )
    }
}

