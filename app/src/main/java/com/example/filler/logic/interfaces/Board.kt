package com.example.filler.logic.interfaces

import com.example.filler.constants.GameColor
import com.example.filler.logic.Position

interface Board {
    val size: Int
    fun getP1Home(): Position
    fun getP2Home (): Position
    fun getColor(position: Position): GameColor
    fun setColor(position: Position, color: GameColor)
    fun toArray(): Array<GameColor>
    fun hasPosition(position: Position): Boolean
}