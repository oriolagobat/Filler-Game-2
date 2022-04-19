package com.example.filler.logic

import com.example.filler.constants.Colors


class Board(
    private val size: Int,
    private val colors: Array<Colors>,
) : BoardInt {

    private var cells = RandomFiller(size, colors).fill()

    override fun getP1Color(): Colors {
        return cells[0][0].color
    }

    override fun getP2Color(): Colors {
        return cells[size - 1][size - 1].color
    }

    override fun getColor(position: Position): Colors {
        return cells[position.x][position.y].color
    }
}