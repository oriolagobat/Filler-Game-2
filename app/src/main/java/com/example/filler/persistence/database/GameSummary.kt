package com.example.filler.persistence.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filler.constants.gui.*
import java.io.Serializable

@Entity
data class GameSummary(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = DB_END_TIME) val endTime: String,
    @ColumnInfo(name = DB_ELAPSED_TIME) val elapsedTime: String,
    @ColumnInfo(name = DB_ALIAS) val alias: String,
    @ColumnInfo(name = DB_OUTCOME) val outcome: String,
    @ColumnInfo(name = DB_GRID_SIZE) val gridSize: Int,
    @ColumnInfo(name = DB_NUM_COLORS) val numColors: Int,
    @ColumnInfo(name = DB_CONQUERED_AREA) val conqueredAreaPercent: Int
) : Serializable
