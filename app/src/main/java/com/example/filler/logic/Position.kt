package com.example.filler.logic

data class Position(val row: Int, val col: Int) {
    fun getAbovePosition(): Position {
        return Position(row - 1, col)
    }

    fun getBelowPosition(): Position {
        return Position(row + 1, col)
    }

    fun getLeftPosition(): Position {
        return Position(row, col - 1)
    }

    fun getRightPosition(): Position {
        return Position(row, col + 1)
    }
}