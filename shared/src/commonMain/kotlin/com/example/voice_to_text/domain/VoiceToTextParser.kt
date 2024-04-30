package com.example.voice_to_text.domain

import com.example.translator_kmm.util.CommonStateFlow

interface VoiceToTextParser {
    val state: CommonStateFlow<VoiceToTextParseState>
    fun startListening(languageCode: String)
    fun stopListening()
    fun cancel()
    fun reset()
}