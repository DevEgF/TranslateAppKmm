package com.example.voice_to_text.presentation.event

sealed class VoiceToTextEvent {
    object Close: VoiceToTextEvent()

    data class PermissionResult(
        val isGranted: Boolean,
        val isPermanentlyDeclined: Boolean
    ): VoiceToTextEvent()

    data class ToggleRecording(
        val languageCode: String
    ): VoiceToTextEvent()

    object Reset: VoiceToTextEvent()
}