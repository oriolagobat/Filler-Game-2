package com.example.filler.persistence.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class GameSummaryRepository(private val wordDao: GameSummaryDAO) {

    val allSummaries: Flow<List<GameSummary>> = wordDao.getAllSummaries()

    @WorkerThread
    suspend fun insert(summary: GameSummary) {
        wordDao.insert(summary)
    }
}
