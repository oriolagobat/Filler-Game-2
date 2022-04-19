package com.example.filler.logic

import com.example.filler.constants.GameColor


class BoardImp(
    private val size: Int,
) : Board {

    private val colors = Array(size) { Array(size) { GameColor.NULL } }

    override fun getP1HomeColor(): GameColor {
        return colors[size - 1][0]
    }

    override fun getP2HomeColor(): GameColor {
        return colors[0][size - 1]
    }

    override fun getColor(position: Position): GameColor {
        return colors[position.row][position.col]
    }

    override fun setColor(position: Position, color: GameColor) {
        colors[position.row][position.col] = color
    }

    override fun getSurroundingColors(position: Position): ArrayList<GameColor> {
        val surroundingColors = ArrayList<GameColor>()
        surroundingColors.add(getAboveColor(position))
        surroundingColors.add(getBelowColor(position))
        surroundingColors.add(getLeftColor(position))
        surroundingColors.add(getRightColor(position))
        return surroundingColors
    }

    private fun getAboveColor(position: Position): GameColor {
        return if (position.row == 0) {
            GameColor.NULL
        } else {
            colors[position.row - 1][position.col]
        }
    }

    private fun getBelowColor (position: Position): GameColor {
        return if (position.row == size - 1) {
            GameColor.NULL
        } else {
            colors[position.row + 1][position.col]
        }
    }

    private fun getLeftColor (position: Position): GameColor {
        return if (position.col == 0) {
            GameColor.NULL
        } else {
            colors[position.row][position.col - 1]
        }
    }

    private fun getRightColor (position: Position): GameColor {
        return if (position.col == size - 1) {
            GameColor.NULL
        } else {
            colors[position.row][position.col + 1]
        }
    }
}