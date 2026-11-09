package com.umair.spinzy.ui.screens

import com.umair.spinzy.i18n.LocalStrings
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.umair.spinzy.data.PromptType
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.ui.components.GlassCard
import com.umair.spinzy.ui.components.shakeOn
import com.umair.spinzy.ui.components.GlassTextField
import com.umair.spinzy.ui.components.GlowButton
import com.umair.spinzy.ui.components.SpinzyTopBar
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.theme.Aqua
import com.umair.spinzy.ui.theme.AquaDeep
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.CoralDeep
import com.umair.spinzy.ui.theme.GlassFill
import com.umair.spinzy.ui.theme.Midnight
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid

@Composable
fun CustomPromptsScreen(state: GameState, vm: GameViewModel) {
    val s = LocalStrings.current
    var text by rememberSaveable { mutableStateOf("") }
    var type by rememberSaveable { mutableStateOf(PromptType.TRUTH) }
    val add = { if (vm.addCustom(text, type)) text = "" }
    val shake = com.umair.spinzy.ui.LocalInputShake.current

    Column(Modifier.fillMaxSize().safeDrawingPadding().imePadding().padding(horizontal = 20.dp)) {
        SpinzyTopBar(s.myQuestionsTitle, s.myQuestionsSub, onBack = { vm.back() })
        Spacer(Modifier.height(8.dp))

        // Segmented Truth / Dare toggle
        Row(
            Modifier.fillMaxWidth().background(GlassFill, RoundedCornerShape(50)).padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            listOf(PromptType.TRUTH to Aqua, PromptType.DARE to Coral).forEach { (t, c) ->
                val bg by animateColorAsState(if (type == t) c else GlassFill.copy(alpha = 0f), spring(stiffness = Spring.StiffnessMediumLow), label = "seg")
                Box(
                    Modifier.weight(1f).height(44.dp).background(bg, RoundedCornerShape(50)).pressable { type = t },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(if (t == PromptType.TRUTH) s.truth else s.dare, style = MaterialTheme.typography.labelLarge, color = if (type == t) Midnight else TextMid)
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        GlassTextField(
            text, { text = it.take(160) },
            if (type == PromptType.TRUTH) s.askJuicy else s.dareThemTo,
            Modifier.fillMaxWidth().shakeOn(shake.count), singleLine = false, capitalization = KeyboardCapitalization.Sentences, onDone = add,
        )
        Spacer(Modifier.height(12.dp))
        GlowButton(
            if (type == PromptType.TRUTH) s.addTruth else s.addDare,
            colors = if (type == PromptType.TRUTH) listOf(Aqua, AquaDeep) else listOf(Coral, CoralDeep),
            textColor = Midnight, height = 54.dp,
            modifier = Modifier.fillMaxWidth(), onClick = add,
        )

        LazyColumn(Modifier.weight(1f), contentPadding = PaddingValues(vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            if (state.customPrompts.isEmpty()) {
                item {
                    Text(s.noCustomYet, color = TextLow, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(16.dp))
                }
            }
            items(state.customPrompts, key = { it.type.name + it.text }) { p ->
                val c = if (p.type == PromptType.TRUTH) Aqua else Coral
                GlassCard(Modifier.fillMaxWidth().animateItem(placementSpec = spring(dampingRatio = 0.8f, stiffness = Spring.StiffnessMediumLow)), shape = RoundedCornerShape(20.dp), borderColor = c.copy(alpha = 0.4f)) {
                    Row(Modifier.padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(10.dp).background(c, RoundedCornerShape(50)))
                        Spacer(Modifier.width(12.dp))
                        Text(p.text, style = MaterialTheme.typography.bodyLarge, color = TextHigh, modifier = Modifier.weight(1f))
                        Box(Modifier.size(40.dp).pressable { vm.removeCustom(p) }, contentAlignment = Alignment.Center) {
                            Icon(Icons.Rounded.Close, contentDescription = s.delete, tint = TextLow)
                        }
                    }
                }
            }
        }
    }
}
