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

    @WorkerThread
    suspend fun getSummaries(): List<GameSummary> {
        return wordDao.getAllSummaries()
    }

    @WorkerThread
    suspend fun getSummariesWithAlias(alias: String): List<GameSummary> {
        return wordDao.getSummariesWithAlias(alias)
    }

    @WorkerThread
    suspend fun byAreaDesc(): List<GameSummary> {
        return wordDao.getAllSummariesOrderedByConqueredAreaDesc()
    }

    @WorkerThread
    suspend fun byAreaAsc(): List<GameSummary> {
        return wordDao.getAllSummariesOrderedByConqueredAreaAsc()
    }

    @WorkerThread
    suspend fun getSummariesByOutcome(outcome: String): List<GameSummary> {
        return wordDao.getSummaryByOutcome(outcome)
    }
}
