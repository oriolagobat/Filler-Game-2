package com.example.filler.logic.ai

import com.example.filler.constants.GameColor
import com.example.filler.logic.board.Board
import com.example.filler.logic.player.PlayerArea

data class AIGeneratorSettings(
    val board: Board,
    val playerArea: PlayerArea,
    val colors: List<GameColor>
)