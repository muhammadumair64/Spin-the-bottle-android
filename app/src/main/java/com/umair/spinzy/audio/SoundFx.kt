package com.umair.spinzy.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.runtime.staticCompositionLocalOf
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sin
import kotlin.random.Random

enum class Sfx { TICK, WHOOSH, LAND, REVEAL, POP, WIN }

/**
 * All sound effects are synthesised in code at first launch (no audio assets, tiny APK),
 * cached as WAV files and played through a low-latency SoundPool.
 */
class SoundFx(context: Context) {
    private val pool: SoundPool = SoundPool.Builder()
        .setMaxStreams(8)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        ).build()
    private val ids = java.util.concurrent.ConcurrentHashMap<Sfx, Int>()
    var enabled: Boolean = true

    init {
        val dir = File(context.cacheDir, "sfx").apply { mkdirs() }
        Thread {
            Sfx.entries.forEach { s ->
                val f = File(dir, "${s.name.lowercase()}_v1.wav")
                if (!f.exists()) writeWav(f, synth(s))
                ids[s] = pool.load(f.path, 1)
            }
        }.start()
    }

    fun play(s: Sfx, volume: Float = 1f, rate: Float = 1f) {
        if (!enabled) return
        val id = ids[s] ?: return
        pool.play(id, volume, volume, 1, 0, rate.coerceIn(0.5f, 2f))
    }

    fun release() = pool.release()

    // ------------------------------------------------------------------ synthesis
    private companion object {
        const val RATE = 44_100

        fun synth(s: Sfx): FloatArray = when (s) {
            Sfx.TICK -> render(0.035f) { t -> sin(2 * PI * 2300 * t).toFloat() * exp(-t / 0.005).toFloat() * 0.55f }
            Sfx.POP -> render(0.09f) { t ->
                val f = 500 + 1400 * (t / 0.09)
                sin(2 * PI * f * t).toFloat() * exp(-t / 0.03).toFloat() * 0.5f
            }
            Sfx.LAND -> render(0.22f) { t ->
                (sin(2 * PI * 140 * t) * exp(-t / 0.06) * 0.8 + sin(2 * PI * 1800 * t) * exp(-t / 0.004) * 0.3).toFloat()
            }
            Sfx.WHOOSH -> {
                var lp = 0f
                render(0.5f) { t ->
                    val x = (t / 0.5).toFloat()
                    val env = sin(PI * x).toFloat().let { it * it }
                    val a = 0.02f + 0.25f * x            // filter opens up = rising "whoosh"
                    lp += a * ((Random.nextFloat() * 2 - 1) - lp)
                    lp * env * 0.9f
                }
            }
            Sfx.REVEAL -> chord(listOf(1046.5, 1318.5, 1568.0), 0.07f, 0.5f)
            Sfx.WIN -> chord(listOf(523.25, 659.25, 783.99, 1046.5, 1318.5), 0.11f, 0.9f)
        }

        /** Arpeggio of bell-ish notes, each starting [gap] seconds after the previous one. */
        fun chord(freqs: List<Double>, gap: Float, len: Float) = render(len + gap * freqs.size) { t ->
            var v = 0.0
            freqs.forEachIndexed { i, f ->
                val tt = t - i * gap
                if (tt >= 0) v += (sin(2 * PI * f * tt) + 0.3 * sin(4 * PI * f * tt)) * exp(-tt / 0.18) * 0.22
            }
            v.toFloat()
        }

        fun render(seconds: Float, f: (Double) -> Float): FloatArray =
            FloatArray((seconds * RATE).toInt()) { i -> f(i / RATE.toDouble()) }

        fun writeWav(file: File, pcm: FloatArray) {
            val data = ByteBuffer.allocate(pcm.size * 2).order(ByteOrder.LITTLE_ENDIAN)
            pcm.forEach { data.putShort((it.coerceIn(-1f, 1f) * Short.MAX_VALUE).toInt().toShort()) }
            val header = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN).apply {
                put("RIFF".toByteArray()); putInt(36 + pcm.size * 2); put("WAVE".toByteArray())
                put("fmt ".toByteArray()); putInt(16); putShort(1); putShort(1)
                putInt(RATE); putInt(RATE * 2); putShort(2); putShort(16)
                put("data".toByteArray()); putInt(pcm.size * 2)
            }
            FileOutputStream(file).use { it.write(header.array()); it.write(data.array()) }
        }
    }
}

/** No-op until provided by SpinzyApp. */
val LocalSfx = staticCompositionLocalOf<SoundFx?> { null }
