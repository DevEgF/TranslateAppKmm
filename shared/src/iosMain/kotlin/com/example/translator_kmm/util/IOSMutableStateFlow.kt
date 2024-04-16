package com.example.translator_kmm.util

import com.example.translator_kmm.util.CommonMutableStateFlow
import kotlinx.coroutines.flow.MutableStateFlow

class IOSMutableStateFlow<T>(
    private val initialValue: T
) : CommonMutableStateFlow<T>(MutableStateFlow(initialValue))