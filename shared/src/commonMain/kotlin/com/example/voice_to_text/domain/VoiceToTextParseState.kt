package com.example.voice_to_text.domain

data class VoiceToTextParseState(
    val result: String = "",
    val error: String? = null,
    val powerRatio: Float = 0f,
    val isSpeaking: Boolean = false,
)
