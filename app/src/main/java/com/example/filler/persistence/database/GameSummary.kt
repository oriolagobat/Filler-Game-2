package com.example.filler.persistence.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameSummary(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val alias: String,
    val outcome: String
)
