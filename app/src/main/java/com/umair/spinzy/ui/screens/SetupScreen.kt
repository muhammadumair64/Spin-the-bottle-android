package com.umair.spinzy.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.umair.spinzy.data.Pack
import com.umair.spinzy.data.Player
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.game.Screen
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.ui.LocalInputShake
import com.umair.spinzy.ui.components.AppIcons
import com.umair.spinzy.ui.components.GlassCard
import com.umair.spinzy.ui.components.GlassTextField
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.IconBadge
import com.umair.spinzy.ui.components.SpinzyTopBar
import com.umair.spinzy.ui.components.Squircle
import com.umair.spinzy.ui.components.enterUp
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.components.shakeOn
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.CoralDeep
import com.umair.spinzy.ui.theme.Cream
import com.umair.spinzy.ui.theme.GlassStroke
import com.umair.spinzy.ui.theme.Midnight
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.PeachLight
import com.umair.spinzy.ui.theme.PlayerColors
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid
import com.umair.spinzy.ui.theme.Wine

/**
 * Setup v2 — everything on one calm screen:
 *  1. quick-add field  2. players as chips  3. all vibes visible in a grid  4. sticky CTA with a summary.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SetupScreen(state: GameState, vm: GameViewModel) {
    val s = LocalStrings.current
    var name by rememberSaveable { mutableStateOf("") }
    val add = { if (vm.addPlayer(name)) name = "" }
    val shake = LocalInputShake.current
    val vibes = state.packs.size + if (state.useCustom && state.customPrompts.isNotEmpty()) 1 else 0

    Column(Modifier.fillMaxSize().safeDrawingPadding().imePadding()) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            SpinzyTopBar(s.whosPlaying, onBack = { vm.back() })
        }
        Column(
            Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(horizontal = 20.dp),
        ) {
            // ---------- players ----------
            SectionHeader(s.sectionPlayers, "${state.players.size}", Modifier.enterUp(0))
            Row(Modifier.enterUp(40), verticalAlignment = Alignment.CenterVertically) {
                GlassTextField(name, { name = it.take(14) }, s.addPlayerHint, Modifier.weight(1f).shakeOn(shake.count), onDone = add)
                Spacer(Modifier.width(10.dp))
                Box(
                    Modifier.size(56.dp).pressable(onClick = add)
                        .background(Brush.linearGradient(listOf(PeachLight, Coral)), Squircle),
                    contentAlignment = Alignment.Center,
                ) { Icon(Icons.Rounded.Add, s.addPlayerHint, tint = Wine) }
            }
            Spacer(Modifier.height(14.dp))
            if (state.players.isEmpty()) {
                EmptyPlayers(s.addAtLeastTwo)
            } else {
                FlowRow(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    state.players.forEach { p -> key(p.id) { PlayerChip(p, vm) } }
                }
                if (state.players.size < 2) {
                    Spacer(Modifier.height(10.dp))
                    Text(s.addAtLeastTwo, style = MaterialTheme.typography.bodyMedium, color = TextLow)
                }
            }

            // ---------- vibes ----------
            Spacer(Modifier.height(26.dp))
            SectionHeader(s.sectionVibes, "$vibes", Modifier.enterUp(80))
            val tiles = Pack.entries.map { it as Pack? } + listOf(null) // null = my questions
            tiles.chunked(2).forEachIndexed { row, pair ->
                Row(Modifier.fillMaxWidth().enterUp(100 + row * 40), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    pair.forEach { pack ->
                        if (pack != null) {
                            VibeTile(pack, pack in state.packs, Modifier.weight(1f)) { vm.togglePack(pack) }
                        } else {
                            CustomTile(state, Modifier.weight(1f), onToggle = { if (state.customPrompts.isEmpty()) vm.go(Screen.CUSTOM) else vm.toggleCustom() }, onEdit = { vm.go(Screen.CUSTOM) })
                        }
                    }
                }
                Spacer(Modifier.height(12.dp))
            }
            Spacer(Modifier.height(12.dp))
        }

        // ---------- sticky CTA ----------
        Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(top = 8.dp, bottom = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedContent(
                s.setupSummary(state.players.size, vibes),
                transitionSpec = { (slideInVertically { it } + fadeIn()) togetherWith (slideOutVertically { -it } + fadeOut()) },
                label = "summary",
            ) { t -> Text(t, style = MaterialTheme.typography.labelLarge, color = TextMid) }
            Spacer(Modifier.height(8.dp))
            GlowButton(
                text = if (state.players.size < 2) s.needMore(2 - state.players.size) else s.letsSpin,
                icon = if (state.canStart) Icons.AutoMirrored.Rounded.ArrowForward else null,
                colors = listOf(Coral, CoralDeep), enabled = state.canStart,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.startGame() },
            )
        }
    }
}

@Composable
private fun key(id: Long, content: @Composable () -> Unit) = androidx.compose.runtime.key(id) { content() }

@Composable
private fun SectionHeader(title: String, count: String, modifier: Modifier) {
    Row(modifier.fillMaxWidth().padding(bottom = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(title, style = MaterialTheme.typography.labelMedium, color = Peach)
        Spacer(Modifier.width(8.dp))
        AnimatedContent(count, transitionSpec = { (slideInVertically { -it } + fadeIn()) togetherWith (slideOutVertically { it } + fadeOut()) }, label = "count") { c ->
            Text(c, style = MaterialTheme.typography.labelMedium, color = Wine,
                modifier = Modifier.clip(RoundedCornerShape(50)).background(Peach).padding(horizontal = 8.dp, vertical = 2.dp))
        }
    }
}

@Composable
private fun EmptyPlayers(text: String) {
    GlassCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), borderColor = GlassStroke) {
        Column(Modifier.fillMaxWidth().padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            IconBadge(Icons.Rounded.PersonAdd, size = 52.dp, bg = Peach, tint = Wine)
            Spacer(Modifier.height(10.dp))
            Text(text, style = MaterialTheme.typography.bodyLarge, color = TextMid, textAlign = TextAlign.Center)
        }
    }
}

/** A compact, poppy chip per player: tap the icon to change it, × to remove (with Undo toast). */
@Composable
private fun PlayerChip(p: Player, vm: GameViewModel) {
    val color = PlayerColors[p.colorIndex % PlayerColors.size]
    val appear = remember { Animatable(0f) }
    LaunchedEffect(Unit) { appear.animateTo(1f, spring(dampingRatio = 0.45f, stiffness = Spring.StiffnessMediumLow)) }
    val spin = remember(p.avatar) { Animatable(0.7f) }
    LaunchedEffect(p.avatar) { spin.animateTo(1f, spring(dampingRatio = 0.35f)) }
    Row(
        Modifier.graphicsLayer { scaleX = appear.value; scaleY = appear.value; alpha = appear.value.coerceIn(0f, 1f) }
            .clip(RoundedCornerShape(50))
            .background(Brush.linearGradient(listOf(color.copy(alpha = 0.28f), Cream.copy(alpha = 0.05f))))
            .border(1.dp, color.copy(alpha = 0.55f), RoundedCornerShape(50))
            .padding(start = 5.dp, end = 4.dp, top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier.size(36.dp).graphicsLayer { scaleX = spin.value; scaleY = spin.value }
                .clip(CircleShape).background(color).pressable { vm.cycleEmoji(p.id) },
            contentAlignment = Alignment.Center,
        ) { Icon(AppIcons.avatar(p.avatar), null, tint = Wine, modifier = Modifier.size(20.dp)) }
        Spacer(Modifier.width(10.dp))
        Text(p.name, style = MaterialTheme.typography.titleMedium, color = TextHigh, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Spacer(Modifier.width(4.dp))
        Box(Modifier.size(32.dp).clip(CircleShape).pressable { vm.removePlayer(p.id) }, contentAlignment = Alignment.Center) {
            Icon(Icons.Rounded.Close, LocalStrings.current.remove(p.name), tint = TextLow, modifier = Modifier.size(18.dp))
        }
    }
}

@Composable
private fun VibeTile(pack: Pack, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    val s = LocalStrings.current
    val scale by animateFloatAsState(if (selected) 1f else 0.96f, spring(dampingRatio = 0.5f, stiffness = Spring.StiffnessMedium), label = "s")
    val border by animateColorAsState(if (selected) pack.color else GlassStroke, label = "b")
    GlassCard(
        modifier.height(112.dp).graphicsLayer { scaleX = scale; scaleY = scale }.pressable(onClick = onClick),
        shape = RoundedCornerShape(26.dp), tint = if (selected) pack.color else Color.Transparent, borderColor = border,
    ) {
        Column(Modifier.fillMaxSize().padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconBadge(AppIcons.pack(pack), size = 36.dp, bg = if (selected) Wine else pack.color.copy(alpha = 0.25f), tint = if (selected) pack.color else TextHigh)
                Spacer(Modifier.weight(1f))
                CheckDot(selected, pack.color)
            }
            Spacer(Modifier.weight(1f))
            Text(s.packTitle(pack), style = MaterialTheme.typography.titleMedium, color = TextHigh, maxLines = 1)
            Spacer(Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                repeat(5) { i ->
                    Box(Modifier.width(14.dp).height(4.dp).background(if (i < pack.heat) pack.color else Color.White.copy(alpha = 0.12f), RoundedCornerShape(50)))
                }
            }
        }
    }
}

@Composable
private fun CustomTile(state: GameState, modifier: Modifier, onToggle: () -> Unit, onEdit: () -> Unit) {
    val s = LocalStrings.current
    val on = state.useCustom && state.customPrompts.isNotEmpty()
    val border by animateColorAsState(if (on) Peach else GlassStroke, label = "b")
    GlassCard(modifier.height(112.dp).pressable(onClick = onToggle), shape = RoundedCornerShape(26.dp), tint = if (on) Peach else Color.Transparent, borderColor = border) {
        Column(Modifier.fillMaxSize().padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconBadge(Icons.Rounded.EditNote, size = 36.dp, bg = if (on) Wine else Peach.copy(alpha = 0.25f), tint = if (on) Peach else TextHigh)
                Spacer(Modifier.weight(1f))
                Text(s.edit, style = MaterialTheme.typography.labelLarge, color = Peach,
                    modifier = Modifier.clip(RoundedCornerShape(50)).pressable(onClick = onEdit).padding(horizontal = 8.dp, vertical = 4.dp))
            }
            Spacer(Modifier.weight(1f))
            Text(s.myQuestions, style = MaterialTheme.typography.titleMedium, color = TextHigh, maxLines = 1)
            Text(
                if (state.customPrompts.isEmpty()) s.tapToWriteOwn else s.savedCount(state.customPrompts.size),
                style = MaterialTheme.typography.bodyMedium, color = TextMid, maxLines = 1, overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun CheckDot(on: Boolean, color: Color) {
    val k by animateFloatAsState(if (on) 1f else 0f, spring(dampingRatio = 0.4f), label = "k")
    Box(Modifier.size(22.dp).clip(CircleShape).border(2.dp, if (on) color else TextLow, CircleShape), contentAlignment = Alignment.Center) {
        Box(Modifier.size(22.dp).graphicsLayer { scaleX = k; scaleY = k }.clip(CircleShape).background(color), contentAlignment = Alignment.Center) {
            Icon(Icons.Rounded.Check, null, tint = Midnight, modifier = Modifier.size(14.dp))
        }
    }
}
