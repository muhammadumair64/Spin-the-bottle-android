package com.umair.spinzy.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.umair.spinzy.audio.LocalSfx
import com.umair.spinzy.audio.Sfx
import com.umair.spinzy.audio.SoundFx
import com.umair.spinzy.data.PromptType
import com.umair.spinzy.game.GameViewModel
import com.umair.spinzy.game.Phase
import com.umair.spinzy.game.Screen
import com.umair.spinzy.game.ToastTone
import com.umair.spinzy.game.UiEvent
import com.umair.spinzy.i18n.LocalStrings
import com.umair.spinzy.ui.components.AuroraBackground
import com.umair.spinzy.ui.components.LocalToaster
import com.umair.spinzy.ui.components.RatingDialog
import com.umair.spinzy.ui.components.ToastHost
import com.umair.spinzy.ui.components.ToasterState
import com.umair.spinzy.ui.screens.CoinScreen
import com.umair.spinzy.ui.screens.CustomPromptsScreen
import com.umair.spinzy.ui.screens.DiceScreen
import com.umair.spinzy.ui.screens.WheelScreen
import com.umair.spinzy.ui.screens.GameScreen
import com.umair.spinzy.ui.screens.HomeScreen
import com.umair.spinzy.ui.screens.LanguageScreen
import com.umair.spinzy.ui.screens.OnboardingScreen
import com.umair.spinzy.ui.screens.ResultsScreen
import com.umair.spinzy.ui.screens.RpsScreen
import com.umair.spinzy.ui.screens.SettingsScreen
import com.umair.spinzy.ui.screens.SetupScreen
import com.umair.spinzy.ui.screens.SplashScreen
import com.umair.spinzy.ui.theme.Aqua
import com.umair.spinzy.ui.theme.Coral
import com.umair.spinzy.ui.theme.Sunflower
import com.umair.spinzy.ui.theme.Violet

private fun Screen.depth() = when (this) {
    Screen.SPLASH -> -2
    Screen.LANGUAGE -> -1
    Screen.ONBOARDING -> -1
    Screen.HOME -> 0
    Screen.SETUP, Screen.SETTINGS, Screen.COIN, Screen.RPS, Screen.DICE, Screen.WHEEL -> 1
    Screen.CUSTOM, Screen.GAME -> 2
    Screen.RESULTS -> 3
}

/** Shared "shake the input" signal for validation feedback. */
class InputShake { var count by mutableStateOf(0) }
val LocalInputShake = androidx.compose.runtime.staticCompositionLocalOf { InputShake() }

@Composable
fun SpinzyApp(vm: GameViewModel = viewModel()) {
    val state by vm.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val sfx = remember { SoundFx(context.applicationContext) }
    DisposableEffect(Unit) { onDispose { sfx.release() } }
    sfx.enabled = state.settings.sound

    val language = state.language
    val strings = language.strings
    val toaster = remember { ToasterState() }
    val inputShake = remember { InputShake() }
    var askRating by remember { mutableStateOf(false) }

    // One-shot feedback from the ViewModel
    LaunchedEffect(Unit) {
        vm.events.collect { e ->
            when (e) {
                is UiEvent.Toast -> {
                    toaster.show(e)
                    sfx.play(if (e.tone == ToastTone.WARNING) Sfx.TICK else Sfx.POP, 0.5f, if (e.tone == ToastTone.WARNING) 0.6f else 1.2f)
                }
                UiEvent.ShakeInput -> inputShake.count++
                UiEvent.AskRating -> askRating = true
            }
        }
    }

    BackHandler(enabled = state.screen != Screen.HOME && state.screen != Screen.SPLASH) { vm.back() }

    // The background glow follows the mood of the current screen / game phase.
    val accent = when {
        state.screen == Screen.GAME && state.phase == Phase.PROMPT ->
            if (state.prompt?.type == PromptType.TRUTH) Aqua else Coral
        state.screen == Screen.RESULTS -> Sunflower
        state.screen == Screen.COIN || state.screen == Screen.DICE -> Sunflower
        state.screen == Screen.RPS || state.screen == Screen.LANGUAGE || state.screen == Screen.WHEEL -> Aqua
        state.screen == Screen.SETUP || state.screen == Screen.CUSTOM -> Violet
        else -> Coral
    }

    CompositionLocalProvider(
        LocalSfx provides sfx,
        LocalStrings provides strings,
        LocalToaster provides toaster,
        LocalInputShake provides inputShake,
        LocalLayoutDirection provides if (language.rtl) LayoutDirection.Rtl else LayoutDirection.Ltr,
    ) {
        AuroraBackground(accent = accent) {
            AnimatedContent(
                targetState = state.screen,
                transitionSpec = {
                    if (initialState == Screen.SPLASH) {
                        ContentTransform(fadeIn(tween(500)) + scaleIn(tween(600), initialScale = 1.06f), fadeOut(tween(400)) + scaleOut(tween(400), targetScale = 0.94f))
                    } else {
                        val forward = targetState.depth() >= initialState.depth()
                        val slide = spring<IntOffset>(dampingRatio = 0.9f, stiffness = Spring.StiffnessMediumLow)
                        ContentTransform(
                            targetContentEnter = slideInHorizontally(slide) { w -> if (forward) w / 3 else -w / 3 } +
                                fadeIn(tween(320)) + scaleIn(tween(380), initialScale = 0.96f),
                            initialContentExit = slideOutHorizontally(slide) { w -> if (forward) -w / 4 else w / 4 } +
                                fadeOut(tween(220)) + scaleOut(tween(300), targetScale = 0.98f),
                        )
                    }
                },
                label = "screens",
            ) { screen ->
                when (screen) {
                    Screen.SPLASH -> SplashScreen(state, vm)
                    Screen.LANGUAGE -> LanguageScreen(state, vm)
                    Screen.ONBOARDING -> OnboardingScreen(state, vm)
                    Screen.HOME -> HomeScreen(state, vm)
                    Screen.SETUP -> SetupScreen(state, vm)
                    Screen.GAME -> GameScreen(state, vm)
                    Screen.RESULTS -> ResultsScreen(state, vm)
                    Screen.CUSTOM -> CustomPromptsScreen(state, vm)
                    Screen.SETTINGS -> SettingsScreen(state, vm)
                    Screen.COIN -> CoinScreen(state, vm)
                    Screen.RPS -> RpsScreen(state, vm)
                    Screen.DICE -> DiceScreen(state, vm)
                    Screen.WHEEL -> WheelScreen(state, vm)
                }
            }

            RatingDialog(
                visible = askRating,
                onRate = { stars ->
                    askRating = false
                    vm.markRated()
                    if (stars >= 4) {
                        toaster.show(ToastTone.SUCCESS) { it.rateThanksHigh }
                        openStore(context)
                    } else {
                        toaster.show(ToastTone.INFO) { it.rateThanksLow }
                        sendFeedback(context, strings.feedbackEmailSubject)
                    }
                },
                onDismiss = { askRating = false },
            )
            ToastHost(toaster)
        }
    }
}

/** Opens the Play Store page (falls back to the web). */
fun openStore(context: android.content.Context) {
    val pkg = context.packageName
    runCatching {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$pkg")).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }.onFailure {
        runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$pkg")).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}

/** Where "Send feedback" emails go — put your support address here. */
const val FEEDBACK_EMAIL = ""

/** Opens the email app with a pre-filled subject. */
fun sendFeedback(context: android.content.Context, subject: String) {
    runCatching {
        context.startActivity(
            Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$FEEDBACK_EMAIL")).putExtra(Intent.EXTRA_SUBJECT, subject)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}
