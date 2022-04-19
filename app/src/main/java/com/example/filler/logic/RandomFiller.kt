package com.example.filler.logic

import com.example.filler.constants.Colors

class RandomFiller(
    private val boardSize: Int,
    private val colors: Array<Colors>,
): Filler {

    override fun fill(): Array<Array<Cell>> {
        val cells = initCells(boardSize)
        return fillCells(cells)
    }

    private fun fillCells(cells: Array<Array<Cell>>): Array<Array<Cell>> {
        for (x in 0 until boardSize) {
            for (y in 0 until boardSize) {
                cells[x][y] = Cell(getRandomColor(), Position(x, y))
            }
        }
        return cells
    }

    private fun initCells(boardSize: Int): Array<Array<Cell>> {
        return Array(boardSize) {
            Array(boardSize) {
                Cell(Colors.RED, Position(0, 0))
            }
        }
    }

    override fun getRandomColor(): Colors {
        return colors[(colors.indices).random()]
    }
}