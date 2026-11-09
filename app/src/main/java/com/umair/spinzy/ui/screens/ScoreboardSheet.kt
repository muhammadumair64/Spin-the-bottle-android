package com.umair.spinzy.ui.screens

import com.umair.spinzy.i18n.LocalStrings
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.umair.spinzy.data.Player
import com.umair.spinzy.ui.components.PlayerBubble
import com.umair.spinzy.ui.theme.Midnight
import com.umair.spinzy.ui.theme.MidnightRaised
import com.umair.spinzy.ui.theme.PlayerColors
import com.umair.spinzy.ui.theme.Sunflower
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid

@Composable
fun ScoreboardSheet(visible: Boolean, players: List<Player>, onDismiss: () -> Unit) {
    val s = LocalStrings.current
    Box(Modifier.fillMaxSize()) {
        AnimatedVisibility(visible, enter = fadeIn(tween(200)), exit = fadeOut(tween(200))) {
            Box(
                Modifier.fillMaxSize().background(Midnight.copy(alpha = 0.6f))
                    .clickable(remember { MutableInteractionSource() }, null, onClick = onDismiss),
            )
        }
        AnimatedVisibility(
            visible,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically(spring<IntOffset>(dampingRatio = 0.8f, stiffness = Spring.StiffnessMediumLow)) { it },
            exit = slideOutVertically(tween(220)) { it },
        ) {
            Column(
                Modifier.fillMaxWidth()
                    .background(MidnightRaised, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .navigationBarsPadding().padding(24.dp),
            ) {
                Box(Modifier.width(44.dp).height(5.dp).background(TextLow, RoundedCornerShape(50)).align(Alignment.CenterHorizontally))
                Spacer(Modifier.height(16.dp))
                Text(s.leaderboard, style = MaterialTheme.typography.headlineMedium, color = TextHigh)
                Spacer(Modifier.height(12.dp))
                players.sortedByDescending { it.score }.forEachIndexed { rank, p ->
                    Row(Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("${rank + 1}", style = MaterialTheme.typography.titleLarge, color = if (rank == 0) Sunflower else TextLow, modifier = Modifier.width(28.dp))
                        PlayerBubble(p.avatar, null, PlayerColors[p.colorIndex % PlayerColors.size], size = 40.dp)
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(p.name, style = MaterialTheme.typography.titleMedium, color = TextHigh)
                            Text(s.statsLine(p.truths, p.dares, p.chickens), style = MaterialTheme.typography.bodyMedium, color = TextMid)
                        }
                        Text("${p.score}", style = MaterialTheme.typography.headlineMedium, color = Sunflower)
                    }
                }
            }
        }
    }
}
