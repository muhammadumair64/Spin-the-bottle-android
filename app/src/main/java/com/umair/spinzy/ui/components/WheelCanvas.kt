package com.umair.spinzy.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.sp
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.Outfit
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.Plum900
import com.umair.spinzy.ui.theme.Wine
import com.umair.spinzy.wheel.WheelColors
import com.umair.spinzy.wheel.WheelItem
import com.umair.spinzy.wheel.WheelState
import com.umair.spinzy.wheel.wheelTextIsLight
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/** One drawn slice. Angles in degrees, 0 = 12 o'clock, clockwise. */
data class SliceUi(val item: WheelItem, val start: Float, val sweep: Float)

/**
 * Slices that MORPH smoothly whenever items are added, removed or re-weighted:
 * old and new shares are interpolated with a spring, leaving items shrink to nothing.
 */
@Composable
fun rememberMorphingSlices(state: WheelState): List<SliceUi> {
    val target: List<Pair<Long, Float>> = state.items.map { it.id to state.fraction(it) }
    val known = remember { mutableMapOf<Long, WheelItem>() }
    state.items.forEach { known[it.id] = it }
    val progress = remember { Animatable(1f) }
    var from by remember { mutableStateOf(target) }
    var to by remember { mutableStateOf(target) }
    LaunchedEffect(target) {
        if (target == to) return@LaunchedEffect
        from = blend(from, to, progress.value)
        to = target
        progress.snapTo(0f)
        progress.animateTo(1f, spring(dampingRatio = 0.72f, stiffness = Spring.StiffnessLow))
    }
    val mixed = blend(from, to, progress.value)
    val total = mixed.sumOf { it.second.toDouble() }.toFloat().coerceAtLeast(0.0001f)
    var acc = 0f
    return mixed.mapNotNull { (id, f) ->
        val item = known[id] ?: return@mapNotNull null
        val sweep = 360f * f / total
        SliceUi(item, acc, sweep).also { acc += sweep }
    }
}

private fun blend(a: List<Pair<Long, Float>>, b: List<Pair<Long, Float>>, t: Float): List<Pair<Long, Float>> {
    val am = a.toMap(); val bm = b.toMap()
    val order = b.map { it.first }.toMutableList()
    a.forEachIndexed { i, (id, _) -> if (id !in bm) order.add(i.coerceAtMost(order.size), id) }
    return order.map { id -> id to ((am[id] ?: 0f) + ((bm[id] ?: 0f) - (am[id] ?: 0f)) * t) }.filter { it.second > 0.0005f }
}

/** Index of the slice under the pointer (12 o'clock) for a given wheel rotation. */
fun sliceAtPointer(slices: List<SliceUi>, rotation: Float): Int {
    if (slices.isEmpty()) return -1
    val theta = (-rotation).mod(360f)
    return slices.indexOfFirst { theta >= it.start && theta < it.start + it.sweep }.takeIf { it >= 0 } ?: slices.lastIndex
}

/** The colourful slices + labels. Rotate it from outside with graphicsLayer. */
@Composable
fun WheelFace(slices: List<SliceUi>, modifier: Modifier = Modifier) {
    val measurer = rememberTextMeasurer()
    Canvas(modifier) {
        val r = size.minDimension / 2f
        val topLeft = Offset(center.x - r, center.y - r)
        if (slices.isEmpty()) {
            for (i in 0 until 6) drawArc(Cream.copy(alpha = if (i % 2 == 0) 0.08f else 0.04f), i * 60f - 90f, 60f, true, topLeft, Size(r * 2, r * 2))
            return@Canvas
        }
        slices.forEach { s ->
            val c = WheelColors[s.item.colorIndex % WheelColors.size]
            drawArc(
                Brush.radialGradient(listOf(c.copy(alpha = 0.85f), c), center = center, radius = r),
                s.start - 90f, s.sweep + 0.4f, true, topLeft, Size(r * 2, r * 2),
            )
        }
        // separators
        if (slices.size > 1) slices.forEach { s ->
            val a = (s.start - 90f) * PI.toFloat() / 180f
            drawLine(Cream.copy(alpha = 0.7f), center, Offset(center.x + r * cos(a), center.y + r * sin(a)), strokeWidth = 3f)
        }
        // labels, written along the radius and reading outward
        slices.forEach { s ->
            if (s.sweep < 7f) return@forEach
            val light = wheelTextIsLight(s.item.colorIndex)
            val fontSize = (r * 0.07f / density + s.sweep * 0.03f).coerceIn(11f, 19f) // in sp
            val layout = measurer.measure(
                s.item.label,
                TextStyle(fontFamily = Outfit, fontWeight = FontWeight.Bold, fontSize = fontSize.sp, color = if (light) Cream else Plum900),
                maxLines = 1, overflow = TextOverflow.Ellipsis,
                constraints = Constraints(maxWidth = (r * 0.62f).toInt()),
            )
            rotate(s.start + s.sweep / 2f - 90f, pivot = center) {
                drawText(layout, topLeft = Offset(center.x + r * 0.90f - layout.size.width, center.y - layout.size.height / 2f))
            }
        }
        // soft depth shading
        drawCircle(Brush.radialGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.18f)), center = center, radius = r), r)
        drawCircle(Brush.linearGradient(listOf(Color.White.copy(alpha = 0.18f), Color.Transparent), start = Offset(center.x - r, center.y - r), end = center), r)
    }
}

/** Static outer rim with chasing marquee lights (faster while spinning). */
@Composable
fun WheelRim(spinning: Boolean, modifier: Modifier = Modifier) {
    val t = rememberInfiniteTransition(label = "lights")
    val chase by t.animateFloat(0f, 1f, infiniteRepeatable(tween(if (spinning) 500 else 1600, easing = LinearEasing)), label = "c")
    Canvas(modifier) {
        val r = size.minDimension / 2f
        drawCircle(Brush.linearGradient(listOf(Peach, Wine, Peach), start = Offset(0f, 0f), end = Offset(size.width, size.height)), r, style = Stroke(r * 0.09f))
        drawCircle(PeachLight.copy(alpha = 0.6f), r * 0.955f, style = Stroke(2f))
        val bulbs = 24
        for (i in 0 until bulbs) {
            val a = (i * 360f / bulbs) * PI.toFloat() / 180f
            val p = Offset(center.x + r * 0.955f * cos(a) * 1f, center.y + r * 0.955f * sin(a))
            val on = ((i + (chase * 3).toInt()) % 3) == 0
            if (on) drawCircle(Brush.radialGradient(listOf(Color(0xFFFFF3C4), Color.Transparent), center = p, radius = r * 0.07f), r * 0.07f, p)
            drawCircle(if (on) Color(0xFFFFF3C4) else Cream.copy(alpha = 0.35f), r * 0.02f, p)
        }
    }
}

/** The arrow at 12 o'clock. [flap] (degrees) kicks it sideways each time a slice edge passes. */
@Composable
fun WheelPointer(flap: Float, modifier: Modifier = Modifier) {
    Box(modifier) {
        Canvas(Modifier.fillMaxSize()) {
            rotate(flap, pivot = Offset(size.width / 2f, size.height * 0.2f)) {
                val w = size.width; val h = size.height
                val path = Path().apply {
                    moveTo(w * 0.5f, h); lineTo(w * 0.1f, h * 0.25f)
                    quadraticTo(w * 0.5f, -h * 0.05f, w * 0.9f, h * 0.25f); close()
                }
                drawPath(path, Color.Black.copy(alpha = 0.3f), style = Stroke(6f))
                drawPath(path, Brush.verticalGradient(listOf(PeachLight, Peach, Color(0xFFF07068))))
                drawCircle(Wine, w * 0.12f, Offset(w * 0.5f, h * 0.3f))
                drawCircle(PeachLight, w * 0.05f, Offset(w * 0.47f, h * 0.27f))
            }
        }
    }
}
