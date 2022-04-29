package com.example.filler.logic.interfaces

import com.example.filler.constants.GameColor
import com.example.filler.logic.GameResponse

interface Game {
    fun initGame(): GameResponse
    fun pickP1Color(color: GameColor): GameResponse
    fun pickP2Color(color: GameColor): GameResponse
    fun pickP2ColorThroughAI(): GameResponse
}