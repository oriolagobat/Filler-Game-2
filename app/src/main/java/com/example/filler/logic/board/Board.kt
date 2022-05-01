package com.example.filler.logic.board

import com.example.filler.constants.GameColor

interface Board {
    fun getNumCols(): Int
    fun getNumCells(): Int
    fun getP1Home(): Position
    fun getP2Home(): Position
    fun getColor(position: Position): GameColor
    fun setColor(position: Position, color: GameColor)
    fun toArray(): Array<GameColor>
    fun hasPosition(position: Position): Boolean
    fun indexToPosition(index: Int): Position
    fun positionToIndex(position: Position): Int
}