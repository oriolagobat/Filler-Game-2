package com.example.filler.logic

import com.example.filler.constants.Colors
import com.example.filler.constants.GameState

data class GameResponse(
    val round: Int,
    val boardColors: Array<Array<Colors>>,
    val chooserColors: Array<Pair<Colors, Boolean>>,
    val state: GameState
)