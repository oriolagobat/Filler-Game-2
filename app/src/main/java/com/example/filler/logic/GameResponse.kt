package com.example.filler.logic

import com.example.filler.constants.GameColor
import com.example.filler.constants.GameState
import java.nio.channels.SelectableChannel

data class GameResponse(
    val round: Int,
    val board: Board,
    val selector: ColorSelector,
    val state: GameState
)