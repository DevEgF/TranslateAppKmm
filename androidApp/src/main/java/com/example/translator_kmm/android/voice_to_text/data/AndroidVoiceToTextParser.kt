package com.example.translator_kmm.android.voice_to_text.data

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.example.translator_kmm.util.CommonStateFlow
import com.example.translator_kmm.util.toCommonStateFlow
import com.example.voice_to_text.domain.VoiceToTextParseState
import com.example.voice_to_text.domain.VoiceToTextParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class AndroidVoiceToTextParser (
    private val app: Application
): VoiceToTextParser, RecognitionListener {

    private val recognizer = SpeechRecognizer.createSpeechRecognizer(app)

    private val _state = MutableStateFlow(VoiceToTextParseState())

    override val state: CommonStateFlow<VoiceToTextParseState>
        get() = _state.toCommonStateFlow()

    override fun startListening(languageCode: String) {
        _state.update { VoiceToTextParseState() }

        if(!SpeechRecognizer.isRecognitionAvailable(app)) {
            _state.update { it.copy(
                error = "Speech recognition is not available"
            ) }
            return
        }
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode)
        }
        recognizer.setRecognitionListener(this)
        recognizer.startListening(intent)
        _state.update { it.copy(
            isSpeaking = true
        ) }
    }

    override fun stopListening() {
        _state.update { VoiceToTextParseState() }
        recognizer.stopListening()
    }

    override fun cancel() {
        recognizer.cancel()
    }

    override fun reset() {
        _state.value = VoiceToTextParseState()
    }

    override fun onReadyForSpeech(params: Bundle?) {
        _state.update { it.copy(error = null) }
    }

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(rmsdB: Float) {
        _state.update { it.copy(
            powerRatio = rmsdB * (1f / (12f - (-2f)))
        ) }
    }

    override fun onBufferReceived(buffer: ByteArray?) = Unit

    override fun onEndOfSpeech() {
        _state.update { it.copy(isSpeaking = false) }
    }

    override fun onError(error: Int) {
        _state.update { it.copy(error =  "Error: $error") }
    }

    override fun onResults(results: Bundle?) {
        results
            ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.getOrNull(0)
            ?.let { text ->
                _state.update {
                    it.copy(
                        result = text
                    )
                }
            }
    }

    override fun onPartialResults(partialResults: Bundle?) = Unit

    override fun onEvent(eventType: Int, params: Bundle?) = Unit

}