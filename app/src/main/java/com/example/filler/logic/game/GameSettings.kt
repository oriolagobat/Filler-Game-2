package com.example.filler.logic.game

import com.example.filler.constants.logic.Difficulty

data class GameSettings(
    val boardSize: Int,
    val nColors: Int,
    val difficulty: Difficulty,
    val hasTimeout: Boolean
)