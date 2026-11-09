package com.umair.spinzy.data

import com.umair.spinzy.data.PromptType.DARE
import com.umair.spinzy.data.PromptType.TRUTH
import com.umair.spinzy.i18n.Language

/** Serves questions from the bank of the selected language (falls back to English per pack). */
object PromptBank {
    fun prompts(packs: Set<Pack>, language: Language = Language.EN): List<Prompt> {
        val set = language.prompts
        val en = Language.EN.prompts
        return packs.flatMap { pack ->
            (set.truths[pack] ?: en.truths[pack]).orEmpty().map { Prompt(it, TRUTH, pack) } +
                (set.dares[pack] ?: en.dares[pack]).orEmpty().map { Prompt(it, DARE, pack) }
        }
    }
}
