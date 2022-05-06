package com.example.filler.logic.game

import com.example.filler.constants.logic.GameColor

interface Game {
    fun pickColorManually(color: GameColor): GameResponse
    fun pickRandomColor(): GameResponse
    fun pickColorThroughAI(): GameResponse
    fun getGameResponse(): GameResponse
}