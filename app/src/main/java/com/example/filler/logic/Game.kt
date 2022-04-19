package com.example.filler.logic

import com.example.filler.constants.GameColor

interface Game {
    fun initGame(): GameResponse
    fun startGame()
    fun pickColor(color: GameColor): GameResponse
}