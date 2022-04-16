package com.example.filler.logic

import com.example.filler.constants.Colors


class Board(
    private val size: Int,
    private val colors: Array<Colors>,
) : BoardInt {

    private var cells: Array<Array<Cell>> = Array(size) { Array(size) { Cell(Colors.RED, 0, 0) } }

    // Initialize the board with random colors
    init {
        for (x in 0 until size) {
            for (y in 0 until size) {
                cells[x][y] = Cell(getRandomColor(), x, y)
            }
        }
    }

    override fun getRandomColor(): Colors {
        return colors[(colors.indices).random()]
    }

    override fun getP1Color(): Colors {
        return cells[0][0].color
    }

    override fun getP2Color(): Colors {
        return cells[size - 1][size - 1].color
    }

    override fun getColor(x: Int, y: Int): Colors {
        return cells[x][y].color
    }
}