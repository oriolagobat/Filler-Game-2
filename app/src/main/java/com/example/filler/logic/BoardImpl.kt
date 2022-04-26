package com.example.filler.logic

import com.example.filler.constants.GameColor
import com.example.filler.logic.interfaces.Board


class BoardImpl(
    private val size: Int,
) : Board {

    private val colors = Array(size) { Array(size) { GameColor.NULL } }

    override fun getP1Home(): Position {
        return Position(size - 1, 0)
    }

    override fun getP2Home(): Position {
        return Position(0, size - 1)
    }

    override fun getColor(position: Position): GameColor {
        return colors[position.row][position.col]
    }

    override fun setColor(position: Position, color: GameColor) {
        colors[position.row][position.col] = color
    }

    override fun toArray(): Array<GameColor> {
        val colorArray = Array(size * size) { GameColor.NULL }
        for (row in 0 until size) {
            for (col in 0 until size) {
                colorArray[row * size + col] = colors[row][col]
            }
        }
        return colorArray
    }

    override fun hasPosition(position: Position): Boolean {
        return position.row < size
                && position.col < size
    }
}