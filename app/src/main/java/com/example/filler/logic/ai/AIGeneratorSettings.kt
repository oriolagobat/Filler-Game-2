package com.example.filler.logic.ai

import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameColor
import com.example.filler.logic.interfaces.Board
import com.example.filler.logic.interfaces.PlayerArea

data class AIGeneratorSettings(
    val board: Board,
    val playerArea: PlayerArea,
    val colors: List<GameColor>
)