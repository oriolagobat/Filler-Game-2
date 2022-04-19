package com.example.filler.logic

import com.example.filler.constants.GameColor

interface Board {
    fun getP1HomeColor(): GameColor
    fun getP2HomeColor(): GameColor
    fun getColor(position: Position): GameColor
    fun setColor(position: Position, color: GameColor)
    fun getSurroundingColors(position: Position): List<GameColor>
}