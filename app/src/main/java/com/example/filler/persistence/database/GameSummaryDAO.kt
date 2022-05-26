package com.example.filler.persistence.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameSummaryDAO {
    @Query("SELECT * FROM gamesummary ")
    fun getAllSummaries(): List<GameSummary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(summary: GameSummary)

    @Query("DELETE FROM gamesummary")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(summary: GameSummary)
}