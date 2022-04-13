package com.example.filler.logic

import com.example.filler.constants.Colors

interface GameInt {
    fun initGame(): GameResponse
    // This initializes the 1st round timer,
    // pickColor() handles the timers on the remaining ones.
    fun startGame()
    fun pickColor(color: Colors): GameResponse
}