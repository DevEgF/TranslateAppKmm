package com.example.translator_kmm.android.voice_to_text.presentation.screen

import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.translator_kmm.android.R
import com.example.translator_kmm.android.core.theme.LightBlue
import com.example.translator_kmm.android.voice_to_text.presentation.component.VoiceRecorderDisplay
import com.example.voice_to_text.presentation.event.VoiceToTextEvent
import com.example.voice_to_text.presentation.state.DisplayState
import com.example.voice_to_text.presentation.state.VoiceToTextState

@Composable
fun VoiceToTextScreen(
    state: VoiceToTextState,
    languageCode: String,
    onResult: (String) -> Unit,
    onEvent: (VoiceToTextEvent) -> Unit
) {
    val context = LocalContext.current
    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            onEvent(
                VoiceToTextEvent.PermissionResult(
                    isGranted = isGranted,
                    isPermanentlyDeclined = !isGranted && (context as ComponentActivity)
                        .shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)
                )
            )
        }
    )
    LaunchedEffect(recordAudioLauncher) {
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                FloatingActionButton(onClick = {
                    if(state.displayState != DisplayState.DISPLAYING_RESULTS) {
                        onEvent(VoiceToTextEvent.ToggleRecording(languageCode))
                    } else {
                        onResult(state.spokenText)
                    }
                },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(75.dp),
                ) {
                    AnimatedContent(targetState = state.displayState, label = "") { displayState ->
                        when(displayState) {
                            DisplayState.SPEAKING -> {
                                Icon(
                                    imageVector = Icons.Rounded.Stop,
                                    contentDescription = "Stop recording",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            DisplayState.DISPLAYING_RESULTS -> {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = "Apply",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            else -> {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.mic),
                                    contentDescription = "Record Audio",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                    }
                }
                if(state.displayState == DisplayState.DISPLAYING_RESULTS) {
                    IconButton(onClick = {
                        onEvent(VoiceToTextEvent.ToggleRecording(languageCode))
                    }) {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Rounded.Refresh,
                            contentDescription = "Recording Again",
                            tint = LightBlue
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = {
                        onEvent(VoiceToTextEvent.Close)
                    },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close"
                    )
                }
                if(state.displayState == DisplayState.SPEAKING) {
                    Text(
                        text = "Listening...",
                        color = LightBlue,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(bottom = 100.dp)
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedContent(targetState = state.displayState, label= "") {displayState ->
                    when(displayState) {
                        DisplayState.WAITING_TO_TALK -> {
                            Text(
                                text = "Click record and start talking",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                        DisplayState.SPEAKING -> {
                            VoiceRecorderDisplay(
                                powerRatios = state.powerRatio,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                            )
                        }
                        DisplayState.DISPLAYING_RESULTS -> {
                            Text(
                                text = state.spokenText,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                        DisplayState.ERROR -> {
                            Text(
                                text = state.recordError ?: "Unknown error",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}