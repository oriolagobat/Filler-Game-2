package com.example.filler.logic

import com.example.filler.constants.GameColor

interface Board {
    fun getP1Home(): Position
    fun getP2Home (): Position
    fun getColor(position: Position): GameColor
    fun setColor(position: Position, color: GameColor)
    fun getSurroundingColors(position: Position): List<GameColor>
    fun getBoardAsColorArray(): Array<GameColor>
}