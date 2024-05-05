package com.example.translator_kmm.android.voice_to_text

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voice_to_text.domain.VoiceToTextParser
import com.example.voice_to_text.presentation.event.VoiceToTextEvent
import com.example.voice_to_text.presentation.viewmodel.VoiceToTextViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidVoiceToTextParser @Inject constructor(
    private val parser : VoiceToTextParser
): ViewModel() {

    private val viewModel by lazy {
        VoiceToTextViewModel(
            parser = parser,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: VoiceToTextEvent) {
        viewModel.onEvent(event)
    }
}