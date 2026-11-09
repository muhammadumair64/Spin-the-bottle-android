package com.umair.spinzy.i18n

import androidx.compose.runtime.staticCompositionLocalOf
import com.umair.spinzy.data.prompts.PromptSet
import com.umair.spinzy.data.prompts.PromptsDe
import com.umair.spinzy.data.prompts.PromptsEn
import com.umair.spinzy.data.prompts.PromptsEs
import com.umair.spinzy.data.prompts.PromptsFr
import com.umair.spinzy.data.prompts.PromptsIt
import com.umair.spinzy.data.prompts.PromptsPt
import com.umair.spinzy.data.prompts.PromptsRu
import com.umair.spinzy.data.prompts.PromptsTr
import com.umair.spinzy.data.prompts.PromptsAr
import com.umair.spinzy.data.prompts.PromptsUr
import com.umair.spinzy.data.prompts.PromptsHi
import com.umair.spinzy.data.prompts.PromptsId
import java.util.Locale

/** Supported languages. Each has its own UI strings and its own question bank. */
enum class Language(
    val code: String,
    val nativeName: String,
    val englishName: String,
    val rtl: Boolean = false,
) {
    EN("en", "English", "English"),
    ES("es", "Español", "Spanish"),
    PT("pt", "Português", "Portuguese"),
    FR("fr", "Français", "French"),
    DE("de", "Deutsch", "German"),
    IT("it", "Italiano", "Italian"),
    RU("ru", "Русский", "Russian"),
    TR("tr", "Türkçe", "Turkish"),
    AR("ar", "العربية", "Arabic", rtl = true),
    UR("ur", "اردو", "Urdu", rtl = true),
    HI("hi", "हिन्दी", "Hindi"),
    ID("id", "Bahasa Indonesia", "Indonesian");

    val strings: Strings get() = LanguageRegistry.strings(this)
    val prompts: PromptSet get() = LanguageRegistry.prompts(this)

    companion object {
        fun fromCode(code: String?): Language? = entries.firstOrNull { it.code == code }

        /** Best match for the phone's language, used to pre-select on first launch. */
        fun deviceDefault(): Language = fromCode(Locale.getDefault().language) ?: EN
    }
}

/** Wires each language to its translation files (filled in step by step; English is the fallback). */
object LanguageRegistry {
    fun strings(l: Language): Strings = when (l) {
        Language.EN -> EnStrings
        Language.ES -> EsStrings
        Language.PT -> PtStrings
        Language.FR -> FrStrings
        Language.DE -> DeStrings
        Language.IT -> ItStrings
        Language.RU -> RuStrings
        Language.TR -> TrStrings
        Language.AR -> ArStrings
        Language.UR -> UrStrings
        Language.HI -> HiStrings
        Language.ID -> IdStrings
    }

    private val cache = java.util.concurrent.ConcurrentHashMap<Language, PromptSet>()

    /** Base bank + the big "more" bank, merged once and cached. */
    fun prompts(l: Language): PromptSet = cache.getOrPut(l) { base(l) + com.umair.spinzy.data.prompts.more.MoreBanks.of(l) }

    private fun base(l: Language): PromptSet = when (l) {
        Language.EN -> PromptsEn
        Language.ES -> PromptsEs
        Language.PT -> PromptsPt
        Language.FR -> PromptsFr
        Language.DE -> PromptsDe
        Language.IT -> PromptsIt
        Language.RU -> PromptsRu
        Language.TR -> PromptsTr
        Language.AR -> PromptsAr
        Language.UR -> PromptsUr
        Language.HI -> PromptsHi
        Language.ID -> PromptsId
    }
}

val LocalStrings = staticCompositionLocalOf<Strings> { EnStrings }
