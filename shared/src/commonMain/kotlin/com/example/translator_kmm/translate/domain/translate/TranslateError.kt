package com.example.translator_kmm.translate.domain.translate

enum class TranslateError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class TranslateException(
    val error: TranslateError
) : Exception(
    "An error occured when translating: $error"
)