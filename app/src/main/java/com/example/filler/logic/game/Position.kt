package com.example.filler.logic.game

data class Position(val row: Int, val col: Int) {

    fun getSurroundingPositions(): MutableList<Position> {
        val surroundingPositions = mutableListOf<Position>()
        surroundingPositions.add(getAbovePosition())
        surroundingPositions.add(getBelowPosition())
        surroundingPositions.add(getLeftPosition())
        surroundingPositions.add(getRightPosition())
        return surroundingPositions
    }

    fun getAbovePosition() = Position(row - 1, col)

    fun getBelowPosition() = Position(row + 1, col)

    fun getLeftPosition() = Position(row, col - 1)

    fun getRightPosition() = Position(row, col + 1)
}