package com.umair.spinzy.data.prompts

import com.umair.spinzy.data.Pack

/** A full Truth-or-Dare question bank for one language: 5 packs × truths + dares. */
class PromptSet(
    val truths: Map<Pack, List<String>>,
    val dares: Map<Pack, List<String>>,
) {
    /** Merges two banks pack by pack (duplicates removed, original order kept). */
    operator fun plus(other: PromptSet) = PromptSet(
        truths = Pack.entries.associateWith { (truths[it].orEmpty() + other.truths[it].orEmpty()).distinct() },
        dares = Pack.entries.associateWith { (dares[it].orEmpty() + other.dares[it].orEmpty()).distinct() },
    )
}

/**
 * Turns a raw-string block (one question per line) into a list.
 * Big banks are stored this way because one string constant per block keeps the generated
 * bytecode tiny (hundreds of listOf() entries would hit the JVM "method too large" limit).
 */
fun bank(block: String): List<String> = block.lineSequence().map { it.trim() }.filter { it.isNotEmpty() }.toList()
