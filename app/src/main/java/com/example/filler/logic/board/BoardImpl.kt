package com.example.filler.logic.board

import com.example.filler.constants.GameColor


class BoardImpl(private val width: Int) : Board {

    private val colors = MutableList(width * width) { GameColor.UNCOLORED }

    override fun getNumCols(): Int = width

    override fun getNumCells(): Int = width * width

    override fun getP1Home(): Position = Position(width - 1, 0)

    override fun getP2Home(): Position = Position(0, width - 1)

    override fun getColor(position: Position): GameColor = colors[positionToIndex(position)]

    override fun setColor(position: Position, color: GameColor) {
        colors[positionToIndex(position)] = color
    }

    override fun positionToIndex(position: Position): Int = position.row * width + position.col

    override fun indexToPosition(index: Int): Position = Position(index / width, index % width)

    override fun toArray(): Array<GameColor> = colors.toTypedArray()

    override fun hasPosition(position: Position): Boolean =
        isValidCoordinate(position.col) && isValidCoordinate(position.row)

    private fun isValidCoordinate(coord: Int): Boolean =  coord >= 0 && coord < this.getNumCols()
}