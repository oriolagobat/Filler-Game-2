package com.example.filler.logic

import com.example.filler.constants.GameColor
import com.example.filler.logic.interfaces.Board


class BoardImpl(
    override val width: Int,
) : Board {

    override val colors = MutableList(width * width) { GameColor.UNCOLORED }

    override fun getP1Home(): Position {
        return Position(width - 1, 0)
    }

    override fun getP2Home(): Position {
        return Position(0, width - 1)
    }

    override fun getColor(position: Position): GameColor {
        val index = positionToIndex(position)
        return colors[index]
    }

    override fun setColor(position: Position, color: GameColor) {
        val index = positionToIndex(position)
        colors[index] = color
    }

    private fun positionToIndex(position: Position): Int {
        return position.row * width + position.col
    }

    override fun toArray(): Array<GameColor> {
        return colors.toTypedArray()
    }

    override fun hasPosition(position: Position): Boolean {
        val index = positionToIndex(position)
        return position.row > 0 && position.col > 0 && index < colors.size
    }
}