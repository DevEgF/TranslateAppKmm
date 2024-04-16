package com.example.translator_kmm.util

import kotlinx.coroutines.flow.StateFlow

actual class CommonStateFlow<T> actual constructor(
    private val stateFlow: StateFlow<T>
) : StateFlow<T> by stateFlow