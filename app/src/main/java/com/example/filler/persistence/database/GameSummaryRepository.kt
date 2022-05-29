package com.example.filler.persistence.database

import androidx.annotation.WorkerThread

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
    fun getSummaries(): List<GameSummary> {
        return wordDao.getAllSummaries()
    }

    @WorkerThread
    fun getSummariesWithAlias(alias: String): List<GameSummary> {
        return wordDao.getSummariesWithAlias(alias)
    }

    @WorkerThread
    fun byAreaDesc(): List<GameSummary> {
        return wordDao.getAllSummariesOrderedByConqueredAreaDesc()
    }

    @WorkerThread
    fun byAreaAsc(): List<GameSummary> {
        return wordDao.getAllSummariesOrderedByConqueredAreaAsc()
    }

    @WorkerThread
    fun getSummariesByOutcome(outcome: String): List<GameSummary> {
        return wordDao.getSummaryByOutcome(outcome)
    }
}
