package com.example.filler.persistence.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class GameSummary(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "endTime") val endTime: String,
    @ColumnInfo(name = "elapsedTime") val elapsedTime: String,
    @ColumnInfo(name = "alias") val alias: String,
    @ColumnInfo(name = "outcome") val outcome: String,
    @ColumnInfo(name = "gridSize") val gridSize: Int,
    @ColumnInfo(name = "numColors") val numColors: Int,
    @ColumnInfo(name = "conqueredArea") val conqueredAreaPercent: Int
) : Serializable
