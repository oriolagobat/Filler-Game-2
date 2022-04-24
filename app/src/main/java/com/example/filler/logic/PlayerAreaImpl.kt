package com.example.filler.logic

class PlayerAreaImpl(initialPosition: Position, private val boardSize: Int) : PlayerArea {

    override val fringe = mutableListOf(initialPosition)
    override val area = mutableListOf(initialPosition)

    override fun addPosition(position: Position) {
        updateArea(position)
        updateFringe(position)
    }

    private fun updateArea(position: Position) {
        area.add(position)
    }

    private fun updateFringe(newPosition: Position) {
        fringe.add(newPosition)
        for (position: Position in fringe)
            if (!isFringePosition(position))
                fringe.remove(position)
    }

    private fun isFringePosition(position: Position): Boolean {
        return (!positionInAreaOrOutsideBoard(position.getAbovePosition())
                && !positionInAreaOrOutsideBoard(position.getBelowPosition())
                && !positionInAreaOrOutsideBoard(position.getLeftPosition())
                && !positionInAreaOrOutsideBoard(position.getRightPosition()))
    }

    private fun positionInAreaOrOutsideBoard(position: Position): Boolean {
        return isOutsideBoard(position) || area.contains(position)
    }

    private fun isOutsideBoard(position: Position): Boolean {
        return position.row >= boardSize || position.col >= boardSize
    }

    override fun hasPosition(position: Position): Boolean {
        return area.contains(position)
    }

}