package com.umair.spinzy.game

import com.umair.spinzy.i18n.Strings

enum class ToastTone { SUCCESS, INFO, WARNING, FIRE }

/** One-shot UI feedback emitted by the ViewModel. Text is resolved against the current language. */
sealed interface UiEvent {
    data class Toast(
        val tone: ToastTone,
        val text: (Strings) -> String,
        val actionLabel: ((Strings) -> String)? = null,
        val action: (() -> Unit)? = null,
        val id: Long = System.nanoTime(),
    ) : UiEvent

    /** Shake the player-name input (validation failed). */
    data object ShakeInput : UiEvent

    /** Ask for a rating (after a few finished games). */
    data object AskRating : UiEvent
}
