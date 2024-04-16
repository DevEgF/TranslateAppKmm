package com.example.translator_kmm.translate.data.mapper

import com.example.translator_kmm.translate.domain.history.HistoryItem
import database.HistoryEntity

fun HistoryEntity.toHistoryItem(): HistoryItem {
    return HistoryItem(
        id,
        fromLanguageCode,
        fromText,
        toLanguageCode,
        toText
    )
}