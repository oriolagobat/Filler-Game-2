package com.example.filler.logic.interfaces

import com.example.filler.constants.GameColor
import com.example.filler.logic.GameResponse

interface Game {
    fun initGame(): GameResponse
    fun startGame()
    fun pickColor(color: GameColor): GameResponse
}