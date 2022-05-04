package com.example.filler.logic.ai

import com.example.filler.logic.board.Board
import com.example.filler.logic.colors.ColorSelector
import com.example.filler.logic.player.PlayerArea

data class AIGeneratorSettings(
    val board: Board,
    val p1Area: PlayerArea,
    val p2Area: PlayerArea,
    val selector: ColorSelector,
)