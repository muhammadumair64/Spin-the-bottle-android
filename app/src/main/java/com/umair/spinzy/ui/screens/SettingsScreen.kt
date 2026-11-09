package com.umair.spinzy.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.rounded.Balance
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.ScreenRotation
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material.icons.rounded.Style
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material.icons.rounded.Vibration
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.umair.spinzy.data.BottleSkin
import com.umair.spinzy.data.Settings
import com.umair.spinzy.game.GameState
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.game.ToastTone
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.i18n.Strings
import com.umair.spinzy.ui.components.GlassCard
import com.umair.spinzy.ui.components.IconBadge
import com.umair.spinzy.ui.components.LocalToaster
import com.umair.spinzy.ui.components.NeonBottle
import com.umair.spinzy.ui.components.RatingDialog
import com.umair.spinzy.ui.components.SpinzyTopBar
import com.umair.spinzy.ui.components.Squircle
import com.umair.spinzy.ui.components.enterUp
import com.umair.spinzy.ui.components.pressable
import com.umair.spinzy.ui.openStore
import com.umair.spinzy.ui.sendFeedback
import com.umair.spinzy.ui.theme.GlassFill
import com.umair.spinzy.ui.theme.GlassStroke
import com.umair.spinzy.ui.theme.Midnight
import com.umair.spinzy.ui.theme.Peach
import com.umair.spinzy.ui.theme.Sunflower
import com.umair.spinzy.ui.theme.TextHigh
import com.umair.spinzy.ui.theme.TextLow
import com.umair.spinzy.ui.theme.TextMid

@Composable
fun SettingsScreen(state: GameState, vm: GameViewModel) {
    val str = LocalStrings.current
    val s = state.settings
    val toaster = LocalToaster.current
    val context = LocalContext.current
    var rating by remember { mutableStateOf(false) }
    fun toggle(name: (Strings) -> String, v: Boolean, f: (Settings) -> Settings) = vm.toggleSetting(name, v, f)

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize().safeDrawingPadding().padding(horizontal = 20.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SpinzyTopBar(str.settings, onBack = { vm.back() })
            SectionLabel(str.sectionBottle)
            SkinPicker(s.skin, Modifier.enterUp(20)) {
                vm.updateSettings(s.copy(skin = it))
                toaster.show(ToastTone.SUCCESS) { t -> t.skinChosen(t.skinTitle(it)) }
            }
            SectionLabel(str.sectionFeel)
            ToggleRow(Icons.AutoMirrored.Rounded.VolumeUp, str.soundEffects, str.soundEffectsSub, s.sound, Modifier.enterUp(40)) { v -> toggle({ it.soundEffects }, v) { it.copy(sound = v) } }
            ToggleRow(Icons.Rounded.Vibration, str.haptics, str.hapticsSub, s.haptics, Modifier.enterUp(60)) { v -> toggle({ it.haptics }, v) { it.copy(haptics = v) } }
            ToggleRow(Icons.Rounded.ScreenRotation, str.shakeToPlay, str.shakeToPlaySub, s.shakeToSpin, Modifier.enterUp(80)) { v -> toggle({ it.shakeToPlay }, v) { it.copy(shakeToSpin = v) } }
            SectionLabel(str.sectionRules)
            ToggleRow(Icons.Rounded.Style, str.wildCards, str.wildCardsSub, s.wildCards, Modifier.enterUp(100)) { v -> toggle({ it.wildCards }, v) { it.copy(wildCards = v) } }
            ToggleRow(Icons.Rounded.Thermostat, str.heatRising, str.heatRisingSub, s.heatEscalation, Modifier.enterUp(120)) { v -> toggle({ it.heatRising }, v) { it.copy(heatEscalation = v) } }
            ToggleRow(Icons.Rounded.Balance, str.fairMode, str.fairModeSub, s.fairMode, Modifier.enterUp(140)) { v -> toggle({ it.fairMode }, v) { it.copy(fairMode = v) } }
            GlassCard(Modifier.fillMaxWidth().enterUp(160), shape = RoundedCornerShape(24.dp)) {
                Column(Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconBadge(Icons.Rounded.Timer, size = 40.dp)
                        Spacer(Modifier.width(14.dp))
                        Column {
                            Text(str.dareTimer, style = MaterialTheme.typography.titleMedium, color = TextHigh)
                            Text(str.dareTimerSub, style = MaterialTheme.typography.bodyMedium, color = TextMid)
                        }
                    }
                    Spacer(Modifier.height(14.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(15, 30, 45, 60).forEach { sec ->
                            val sel = s.dareSeconds == sec
                            val bg by animateColorAsState(if (sel) Sunflower else GlassFill, label = "t")
                            Box(
                                Modifier.weight(1f).height(44.dp).background(bg, RoundedCornerShape(50))
                                    .pressable { vm.updateSettings(s.copy(dareSeconds = sec)) },
                                contentAlignment = Alignment.Center,
                            ) { Text(str.seconds(sec), style = MaterialTheme.typography.labelLarge, color = if (sel) Midnight else TextMid) }
                        }
                    }
                }
            }
            SectionLabel(str.sectionApp)
            LinkRow(Icons.Rounded.Language, str.language, state.language.nativeName, Modifier.enterUp(180)) { vm.openLanguageFromSettings() }
            LinkRow(Icons.Rounded.School, str.replayTutorial, str.replayTutorialSub, Modifier.enterUp(200)) { vm.openOnboardingFromSettings() }
            LinkRow(Icons.Rounded.StarRate, str.rateApp, str.rateAppSub, Modifier.enterUp(220)) { rating = true }
            LinkRow(Icons.Rounded.MailOutline, str.sendFeedback, str.sendFeedbackSub, Modifier.enterUp(240)) { sendFeedback(context, str.feedbackEmailSubject) }
            Spacer(Modifier.height(8.dp))
            Text(
                str.footer, style = MaterialTheme.typography.bodyMedium, color = TextLow,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 20.dp),
            )
        }
        RatingDialog(
            visible = rating,
            onRate = { stars ->
                rating = false; vm.markRated()
                if (stars >= 4) { toaster.show(ToastTone.SUCCESS) { it.rateThanksHigh }; openStore(context) }
                else { toaster.show(ToastTone.INFO) { it.rateThanksLow }; sendFeedback(context, str.feedbackEmailSubject) }
            },
            onDismiss = { rating = false },
        )
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(text, style = MaterialTheme.typography.labelMedium, color = Peach, modifier = Modifier.padding(top = 10.dp, start = 4.dp))
}

@Composable
private fun SkinPicker(selected: BottleSkin, modifier: Modifier, onPick: (BottleSkin) -> Unit) {
    val str = LocalStrings.current
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        BottleSkin.entries.forEach { skin ->
            val sel = skin == selected
            val border by animateColorAsState(if (sel) skin.glow else GlassStroke, label = "skin")
            val tilt by animateFloatAsState(if (sel) 0f else -18f, spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessLow), label = "tilt")
            GlassCard(
                Modifier.weight(1f).height(150.dp).pressable { onPick(skin) },
                shape = Squircle, borderColor = border, tint = if (sel) skin.glow else Color.Transparent,
            ) {
                Column(Modifier.fillMaxSize().padding(vertical = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    NeonBottle(Modifier.width(30.dp).height(87.dp).graphicsLayer { rotationZ = tilt }, skin = skin)
                    Spacer(Modifier.weight(1f))
                    Text(str.skinTitle(skin), style = MaterialTheme.typography.labelLarge, color = if (sel) TextHigh else TextMid, maxLines = 1)
                }
            }
        }
    }
}

@Composable
private fun ToggleRow(icon: ImageVector, title: String, sub: String, on: Boolean, modifier: Modifier, onChange: (Boolean) -> Unit) {
    GlassCard(modifier.fillMaxWidth().pressable { onChange(!on) }, shape = RoundedCornerShape(24.dp)) {
        Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
            IconBadge(icon, size = 40.dp)
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium, color = TextHigh)
                Text(sub, style = MaterialTheme.typography.bodyMedium, color = TextMid)
            }
            Spacer(Modifier.width(10.dp))
            VelvetSwitch(on)
        }
    }
}

@Composable
private fun LinkRow(icon: ImageVector, title: String, sub: String, modifier: Modifier, onClick: () -> Unit) {
    GlassCard(modifier.fillMaxWidth().pressable(onClick = onClick), shape = RoundedCornerShape(24.dp)) {
        Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
            IconBadge(icon, size = 40.dp)
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium, color = TextHigh)
                Text(sub, style = MaterialTheme.typography.bodyMedium, color = TextMid)
            }
            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, null, tint = TextLow)
        }
    }
}

@Composable
private fun VelvetSwitch(on: Boolean) {
    val x by animateDpAsState(if (on) 24.dp else 2.dp, spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMedium), label = "x")
    val track by animateColorAsState(if (on) Peach else GlassFill, label = "track")
    val knob by animateDpAsState(if (on) 26.dp else 22.dp, spring(dampingRatio = 0.4f), label = "knob")
    Box(Modifier.width(52.dp).height(30.dp).background(track, RoundedCornerShape(50)), contentAlignment = Alignment.CenterStart) {
        Box(Modifier.offset(x = x).size(knob).background(if (on) Midnight else TextMid, CircleShape))
    }
}
