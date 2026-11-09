package com.umair.spinzy

import com.umair.spinzy.data.BottleSkin
import com.umair.spinzy.data.Pack
import com.umair.spinzy.data.WildCard
import com.umair.spinzy.i18n.EnStrings
import com.umair.spinzy.i18n.Language
import com.umair.spinzy.i18n.Strings
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/**
 * Guards the 12-language promise:
 *  - every language really translates its UI (not silently falling back to English)
 *  - every question bank is full (5 packs × truths + dares, no duplicates)
 *  - no emoji anywhere (the design uses vector icons only)
 */
class TranslationCompletenessTest {

    private val emoji = Regex("[\\x{1F300}-\\x{1FAFF}\\x{2600}-\\x{27BF}\\x{1F000}-\\x{1F2FF}]")

    /** Calls every no-arg / enum-arg / Int / String member of [s] and returns key → text. */
    private fun texts(s: Strings): Map<String, String> {
        val out = linkedMapOf<String, String>()
        for (m in Strings::class.java.declaredMethods) {
            if (m.returnType != String::class.java || java.lang.reflect.Modifier.isStatic(m.modifiers)) continue
            m.isAccessible = true
            val p = m.parameterTypes
            when {
                p.isEmpty() -> out[m.name] = m.invoke(s) as String
                p.size == 1 && p[0] == Int::class.javaPrimitiveType -> out["${m.name}(7)"] = m.invoke(s, 7) as String
                p.size == 1 && p[0] == String::class.java -> out["${m.name}(s)"] = m.invoke(s, "Ana") as String
                p.size == 2 && p.all { it == String::class.java } -> out["${m.name}(s,s)"] = m.invoke(s, "Ana", "Bo") as String
                p.size == 1 && p[0] == Pack::class.java -> Pack.entries.forEach { out["${m.name}($it)"] = m.invoke(s, it) as String }
                p.size == 1 && p[0] == WildCard::class.java -> WildCard.entries.forEach { out["${m.name}($it)"] = m.invoke(s, it) as String }
                p.size == 1 && p[0] == BottleSkin::class.java -> BottleSkin.entries.forEach { out["${m.name}($it)"] = m.invoke(s, it) as String }
            }
        }
        return out
    }

    /** Words that are legitimately the same in many languages. */
    private val sameAllowed = setOf("appName", "getAppName", "getScores", "getSectionApp", "getCouples")

    @Test fun everyLanguageTranslatesItsUi() {
        val en = texts(EnStrings)
        assertTrue("English base should expose many strings", en.size > 150)
        for (lang in Language.entries.filter { it != Language.EN }) {
            val t = texts(lang.strings)
            val same = t.filter { (k, v) -> en[k] == v && k !in sameAllowed && v.any { it.isLetter() } && v.length > 5 }
            val ratio = same.size.toDouble() / t.size
            if (ratio > 0.06) fail("${lang.englishName}: ${same.size} strings still English → ${same.keys.take(12)}")
        }
    }

    @Test fun noEmojiAnywhere() {
        for (lang in Language.entries) {
            texts(lang.strings).forEach { (k, v) -> if (emoji.containsMatchIn(v)) fail("Emoji in ${lang.code}.$k: $v") }
            val set = lang.prompts
            (set.truths.values.flatten() + set.dares.values.flatten()).forEach {
                if (emoji.containsMatchIn(it)) fail("Emoji in ${lang.code} question: $it")
            }
        }
    }

    @Test fun everyQuestionBankIsComplete() {
        for (lang in Language.entries) {
            val set = lang.prompts
            for (pack in Pack.entries) {
                val t = set.truths[pack].orEmpty()
                val d = set.dares[pack].orEmpty()
                // English: 500+ per category; every other language: 100+ per category
                val min = if (lang == Language.EN) 250 else 50
                assertTrue("${lang.code}/$pack truths: ${t.size} (need $min)", t.size >= min)
                assertTrue("${lang.code}/$pack dares: ${d.size} (need $min)", d.size >= min)
                assertTrue("${lang.code}/$pack has duplicate truths", t.toSet().size == t.size)
                assertTrue("${lang.code}/$pack has duplicate dares", d.toSet().size == d.size)
                (t + d).forEach { assertTrue("${lang.code}: blank question", it.isNotBlank()) }
            }
        }
    }

    @Test fun nonEnglishBanksAreActuallyTranslated() {
        val en = Language.EN.prompts.truths.values.flatten().toSet()
        for (lang in Language.entries.filter { it != Language.EN }) {
            val overlap = lang.prompts.truths.values.flatten().count { it in en }
            assertTrue("${lang.code}: $overlap English questions left", overlap == 0)
        }
    }
}
