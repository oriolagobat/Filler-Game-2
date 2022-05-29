package com.example.filler.persistence.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameSummaryDAO {
    @Query("SELECT * FROM gamesummary ")
    fun getAllSummaries(): List<GameSummary>

    @Query("SELECT * FROM gamesummary WHERE alias = :alias")
    fun getSummariesWithAlias(alias: String): List<GameSummary>

    @Query("SELECT * FROM gamesummary ORDER BY conqueredArea DESC")
    fun getAllSummariesOrderedByConqueredAreaDesc(): List<GameSummary>

    @Query("SELECT * FROM gamesummary ORDER BY conqueredArea ASC")
    fun getAllSummariesOrderedByConqueredAreaAsc(): List<GameSummary>

    @Query("SELECT * FROM gamesummary WHERE outcome = 'P1_WON'")
    fun getAllVictorySummaries(): List<GameSummary>

    @Query("SELECT * FROM gamesummary WHERE outcome = 'P22WON'")
    fun getAllDefeatSummaries(): List<GameSummary>

    @Query("SELECT * FROM gamesummary WHERE outcome = 'DRAW'")
    fun getAllDrawSummaries(): List<GameSummary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(summary: GameSummary)

    @Query("DELETE FROM gamesummary")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(summary: GameSummary)
}