package com.umair.spinzy.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Flip
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.Psychology
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.IconBadge
import com.umair.spinzy.ui.components.NeonBottle
import com.umair.spinzy.ui.components.Rps
import com.umair.spinzy.ui.components.RpsGlyph
import com.umair.spinzy.ui.components.Squircle
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.theme.Apricot
import com.umair.spinzy.ui.theme.Blush
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.CoralDeep
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.Lilac
import com.umair.spinzy.ui.theme.Mauve
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.Raspberry
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun OnboardingScreen(state: GameState, vm: GameViewModel) {
    val s = LocalStrings.current
    val pages = listOf(s.ob1Title to s.ob1Body, s.ob2Title to s.ob2Body, s.ob3Title to s.ob3Body, s.ob4Title to s.ob4Body)
    val pager = rememberPagerState { pages.size }
    val scope = rememberCoroutineScope()
    val last = pager.currentPage == pages.lastIndex

    Column(Modifier.fillMaxSize().safeDrawingPadding()) {
        Row(Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 20.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("Spinzy", style = MaterialTheme.typography.titleLarge, color = TextHigh)
            Spacer(Modifier.weight(1f))
            AnimatedVisibility(!last, enter = fadeIn(), exit = fadeOut()) {
                Text(s.skip, style = MaterialTheme.typography.labelLarge, color = TextMid,
                    modifier = Modifier.clip(RoundedCornerShape(50)).pressable { vm.onboardingDone() }.padding(horizontal = 14.dp, vertical = 10.dp))
            }
        }
        HorizontalPager(pager, Modifier.weight(1f)) { page ->
            val offset = (pager.currentPage - page) + pager.currentPageOffsetFraction
            Column(
                Modifier.fillMaxSize().padding(horizontal = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            ) {
                // Illustration moves faster than the text → depth / parallax
                Box(
                    Modifier.size(300.dp).graphicsLayer {
                        translationX = offset * size.width * 0.45f
                        val sc = 1f - 0.25f * offset.absoluteValue.coerceAtMost(1f)
                        scaleX = sc; scaleY = sc
                        alpha = 1f - 0.6f * offset.absoluteValue.coerceAtMost(1f)
                        rotationZ = offset * -10f
                    },
                    contentAlignment = Alignment.Center,
                ) {
                    when (page) {
                        0 -> IlloBottle(state)
                        1 -> IlloCards()
                        2 -> IlloOrbit()
                        else -> IlloGames()
                    }
                }
                Spacer(Modifier.height(28.dp))
                Text(
                    pages[page].first, style = MaterialTheme.typography.displaySmall, color = TextHigh, textAlign = TextAlign.Center,
                    modifier = Modifier.graphicsLayer { translationX = offset * size.width * 0.15f; alpha = 1f - offset.absoluteValue },
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    pages[page].second, style = MaterialTheme.typography.bodyLarge, color = TextMid, textAlign = TextAlign.Center,
                    modifier = Modifier.graphicsLayer { translationX = offset * size.width * 0.08f; alpha = 1f - offset.absoluteValue },
                )
            }
        }
        // morphing page indicator
        Row(Modifier.fillMaxWidth().padding(vertical = 18.dp), horizontalArrangement = Arrangement.Center) {
            repeat(pages.size) { i ->
                val active = i == pager.currentPage
                val w by animateDpAsState(if (active) 30.dp else 8.dp, spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow), label = "w")
                val c by animateColorAsState(if (active) Peach else TextLow.copy(alpha = 0.5f), label = "c")
                Box(Modifier.padding(horizontal = 4.dp).width(w).height(8.dp).clip(RoundedCornerShape(50)).background(c))
            }
        }
        AnimatedContent(last, transitionSpec = { (fadeIn() + scaleIn(spring(dampingRatio = 0.6f), initialScale = 0.9f)) togetherWith fadeOut() }, label = "cta",
            modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 14.dp)) { isLast ->
            GlowButton(
                if (isLast) s.getStarted else s.next,
                colors = listOf(Coral, CoralDeep), icon = Icons.AutoMirrored.Rounded.ArrowForward,
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (isLast) vm.onboardingDone() else scope.launch { pager.animateScrollToPage(pager.currentPage + 1, animationSpec = spring(dampingRatio = 0.85f, stiffness = Spring.StiffnessLow)) }
            }
        }
    }
}

// ───── Illustrations (all vector / canvas, all animated) ─────

@Composable
private fun IlloBottle(state: GameState) {
    val t = rememberInfiniteTransition(label = "b")
    val rot by t.animateFloat(0f, 360f, infiniteRepeatable(tween(4200, easing = LinearEasing)), label = "r")
    val icons = listOf(Icons.Rounded.Star, Icons.Rounded.Favorite, Icons.Rounded.Pets, Icons.Rounded.Bolt)
    val colors = listOf(Peach, Coral, Blush, Lilac)
    Box(Modifier.size(280.dp).clip(Squircle).background(Brush.radialGradient(listOf(Cream.copy(alpha = 0.10f), Cream.copy(alpha = 0.02f)))), contentAlignment = Alignment.Center) {
        icons.forEachIndexed { i, ic ->
            val a = (i * 90f - 90f) * PI.toFloat() / 180f
            Box(Modifier.offset((105 * cos(a)).dp, (105 * sin(a)).dp)) { Avatar(ic, colors[i]) }
        }
        NeonBottle(Modifier.size(46.dp, 134.dp).graphicsLayer { rotationZ = rot }, skin = state.settings.skin)
    }
}

@Composable
private fun Avatar(icon: ImageVector, color: androidx.compose.ui.graphics.Color) {
    Box(Modifier.size(52.dp).clip(Squircle).background(Brush.linearGradient(listOf(color, color.copy(alpha = 0.7f)))), contentAlignment = Alignment.Center) {
        Icon(icon, null, tint = Wine, modifier = Modifier.size(26.dp))
    }
}

@Composable
private fun IlloCards() {
    val t = rememberInfiniteTransition(label = "c")
    val rock by t.animateFloat(-1f, 1f, infiniteRepeatable(tween(1800), RepeatMode.Reverse), label = "r")
    val flip by t.animateFloat(0f, 360f, infiniteRepeatable(tween(3600, easing = LinearEasing)), label = "f")
    Box(Modifier.size(300.dp), contentAlignment = Alignment.Center) {
        Card(Icons.Rounded.Psychology, listOf(PeachLight, Blush, Mauve), Modifier.offset((-48).dp, 10.dp).graphicsLayer { rotationZ = -12f + rock * 4f })
        Card(Icons.Rounded.Bolt, listOf(Peach, Coral, Raspberry), Modifier.offset(48.dp, (-10).dp).graphicsLayer {
            rotationZ = 10f - rock * 4f; rotationY = if (flip in 150f..210f) flip - 180f else 0f; cameraDistance = 12f * density
        })
    }
}

@Composable
private fun Card(icon: ImageVector, colors: List<androidx.compose.ui.graphics.Color>, modifier: Modifier) {
    Box(
        modifier.size(150.dp, 200.dp).graphicsLayer { shadowElevation = 30f; shape = Squircle; clip = true; ambientShadowColor = colors[1]; spotShadowColor = colors[1] }
            .background(Brush.linearGradient(colors)),
        contentAlignment = Alignment.Center,
    ) { IconBadge(icon, size = 64.dp, bg = Wine) }
}

@Composable
private fun IlloOrbit() {
    val t = rememberInfiniteTransition(label = "o")
    val a by t.animateFloat(0f, 360f, infiniteRepeatable(tween(9000, easing = LinearEasing)), label = "a")
    val pulse by t.animateFloat(0.92f, 1.08f, infiniteRepeatable(tween(900), RepeatMode.Reverse), label = "p")
    val items = listOf(Icons.Rounded.Bolt to Peach, Icons.Rounded.Groups to Blush, Icons.Rounded.SwapHoriz to Apricot, Icons.Rounded.Flip to Lilac, Icons.Rounded.Shield to PeachLight)
    Box(Modifier.size(300.dp), contentAlignment = Alignment.Center) {
        items.forEachIndexed { i, (ic, c) ->
            val ang = (a + i * 72f) * PI.toFloat() / 180f
            Box(Modifier.offset((112 * cos(ang)).dp, (112 * sin(ang)).dp)) { IconBadge(ic, size = 54.dp, bg = c, tint = Wine) }
        }
        IconBadge(Icons.Rounded.LocalFireDepartment, Modifier.graphicsLayer { scaleX = pulse; scaleY = pulse }, size = 110.dp, bg = Coral, tint = Cream)
    }
}

@Composable
private fun IlloGames() {
    val t = rememberInfiniteTransition(label = "g")
    val coin by t.animateFloat(0f, 360f, infiniteRepeatable(tween(1600, easing = LinearEasing)), label = "c")
    val bounce by t.animateFloat(0f, 1f, infiniteRepeatable(tween(700), RepeatMode.Reverse), label = "b")
    val dice by t.animateFloat(0f, 360f, infiniteRepeatable(tween(2600, easing = LinearEasing)), label = "d")
    Box(Modifier.size(300.dp), contentAlignment = Alignment.Center) {
        // coin
        Box(
            Modifier.offset((-70).dp, (-60).dp).size(110.dp).graphicsLayer { rotationX = coin; cameraDistance = 12f * density; translationY = -bounce * 30f }
                .clip(androidx.compose.foundation.shape.CircleShape).background(Brush.linearGradient(listOf(PeachLight, Peach, Apricot))),
            contentAlignment = Alignment.Center,
        ) { Text("A", style = MaterialTheme.typography.displaySmall, color = Wine) }
        // die
        Box(
            Modifier.offset(75.dp, (-20).dp).size(96.dp).graphicsLayer { rotationZ = dice; shadowElevation = 20f; shape = Squircle; clip = true }
                .background(Brush.linearGradient(listOf(Cream, PeachLight))),
            contentAlignment = Alignment.Center,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) { repeat(2) { Box(Modifier.size(14.dp).clip(androidx.compose.foundation.shape.CircleShape).background(Wine)) } }
        }
        // scissors
        Box(
            Modifier.offset((-10).dp, 85.dp).size(96.dp).graphicsLayer { rotationZ = -20f + bounce * 40f }
                .clip(Squircle).background(Brush.linearGradient(listOf(Blush, Mauve))),
            contentAlignment = Alignment.Center,
        ) { RpsGlyph(Rps.SCISSORS, Wine, Modifier.size(52.dp)) }
    }
}
