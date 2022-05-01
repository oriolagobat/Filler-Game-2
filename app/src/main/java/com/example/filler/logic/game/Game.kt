package com.example.filler.logic.game

import com.example.filler.constants.GameColor
import com.example.filler.logic.game.GameResponse

interface Game {
    fun pickColorManually(color: GameColor): GameResponse
    fun pickColorThroughAI(): GameResponse
    fun getGameResponse(): GameResponse
}