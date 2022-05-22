package com.example.filler.persistence.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameSummaryDAO {
    @Query("SELECT * FROM gamesummary ")
    fun getAllSummaries(): Flow<List<GameSummary>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(summary: GameSummary)

    @Query("DELETE FROM gamesummary")
    suspend fun deleteAll()
}