package com.example.filler.logic

import com.example.filler.constants.GameState
import com.example.filler.logic.interfaces.Board
import com.example.filler.logic.interfaces.ColorSelector

data class GameResponse(
    val round: Int,
    val board: Board,
    val selector: ColorSelector,
    val state: GameState
)