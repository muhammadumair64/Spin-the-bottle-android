package com.umair.spinzy.data.prompts.more

import com.umair.spinzy.data.prompts.PromptSet
import com.umair.spinzy.i18n.Language

/** Extra questions per language (see the More*.kt files). Empty until a language's file is added. */
object MoreBanks {
    private val empty = PromptSet(emptyMap(), emptyMap())
    fun of(l: Language): PromptSet = when (l) {
        Language.EN -> MoreEn
        Language.ES -> MoreEs
        Language.PT -> MorePt
        Language.FR -> MoreFr
        Language.DE -> MoreDe
        Language.IT -> MoreIt
        Language.RU -> MoreRu
        Language.TR -> MoreTr
        Language.AR -> MoreAr
        Language.UR -> MoreUr
        Language.HI -> MoreHi
        Language.ID -> MoreId
        else -> empty
    }
}
