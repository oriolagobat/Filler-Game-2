package com.example.filler.logic

import com.example.filler.constants.Difficulty

data class GameSettings(
    val boardSize: Int,
    val nColors: Int,
    val difficulty: Difficulty
)