package com.example.filler.persistence.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameSummary(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val endTime: String,
    val elapsedTime: String,
    val alias: String,
    val outcome: String,
    val gridSize: Int,
    val numColors: Int,
    val conqueredAreaPercent: Int
)
