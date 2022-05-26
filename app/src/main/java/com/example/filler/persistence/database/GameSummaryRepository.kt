package com.example.filler.persistence.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class GameSummaryRepository(private val wordDao: GameSummaryDAO) {

    @WorkerThread
    suspend fun insert(summary: GameSummary) {
        wordDao.insert(summary)
    }

    @WorkerThread
    suspend fun delete(summary: GameSummary) {
        wordDao.delete(summary)
    }

    suspend fun getSummaries(): List<GameSummary> {
        return wordDao.getAllSummaries()
    }
}
