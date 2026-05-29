package com.example.konuma_speaking.util

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class TtsManager(context: Context) {
    private var tts: TextToSpeech? = null
    private var isInitialized = false

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                isInitialized = true
            }
        }
    }

    fun speak(text: String, isTurkish: Boolean, isSlow: Boolean = false) {
        if (!isInitialized) return
        
        val locale = if (isTurkish) Locale("tr", "TR") else Locale.US
        tts?.language = locale
        tts?.setSpeechRate(if (isSlow) 0.5f else 1.0f)
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
