package com.example.translator_kmm.translate.data.history

import com.example.translator_kmm.database.TranslateDatabase
import com.example.translator_kmm.translate.domain.history.HistoryDataSource
import com.example.translator_kmm.translate.domain.history.HistoryItem
import com.example.translator_kmm.util.CommonFlow
import com.example.translator_kmm.util.toCommonFlow
import com.example.translator_kmm.translate.data.mapper.toHistoryItem
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(
    private val db: TranslateDatabase
) : HistoryDataSource {
    private val queries = db.translateQueries

    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return queries
            .getHistory()
            .asFlow()
            .mapToList()
            .map { history ->
                history.map {
                    it.toHistoryItem()
                }
            }
            .toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timeStamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}