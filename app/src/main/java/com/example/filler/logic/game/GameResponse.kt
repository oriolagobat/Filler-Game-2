package com.example.filler.logic.game

import com.example.filler.constants.GameState
import com.example.filler.logic.board.Board
import com.example.filler.logic.colors.ColorSelector

data class GameResponse(
    val round: Int,
    val board: Board,
    val selector: ColorSelector,
    val state: GameState
)