package com.example.filler.logic

import com.example.filler.constants.GameColor
import com.example.filler.constants.GameState

data class GameResponse(
    val round: Int,
    val boardColors: Array<Array<GameColor>>,
    val chooserColors: Array<Pair<GameColor, Boolean>>,
    val state: GameState
)