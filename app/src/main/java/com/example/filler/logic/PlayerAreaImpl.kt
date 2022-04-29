package com.example.filler.logic

import com.example.filler.logic.interfaces.Board
import com.example.filler.logic.interfaces.PlayerArea

class PlayerAreaImpl(
    initialPosition: Position,
    private val board: Board
) : PlayerArea {

    override val fringe = mutableSetOf(initialPosition)
    override val area = mutableSetOf(initialPosition)

    override fun addPosition(position: Position) {
        updateArea(position)
        updateFringe(position)
    }

    private fun updateArea(position: Position) {
        area.add(position)
    }

    private fun updateFringe(newPosition: Position) {
        fringe.add(newPosition)
        val positionsToRemove = fringe
            .filter { !isFringePosition(it) }
            .toMutableList()
        fringe.removeAll(positionsToRemove)
    }

    private fun isFringePosition(position: Position): Boolean {
        val surroundingPositions = position.getSurroundingPositions()
        return surroundingPositions
            .any { !this.hasPosition(it) && board.hasPosition(it) }
    }

    override fun hasPosition(position: Position): Boolean {
        return area.contains(position)
    }

}