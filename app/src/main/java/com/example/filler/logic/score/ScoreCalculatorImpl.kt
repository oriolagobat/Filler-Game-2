package com.example.filler.logic.score

import com.example.filler.constants.GameColor
import com.example.filler.logic.board.Board
import com.example.filler.logic.game.Position
import com.example.filler.logic.player.PlayerArea

class ScoreCalculatorImpl(
    private val board: Board,
    private var currentArea: PlayerArea,
    private var enemyArea: PlayerArea
) : ScoreCalculator {

    override fun updateAreas(pickedColor: GameColor) {
        updateAreaColor(pickedColor)
        addValidPositions()
        swapAreas()
    }

    private fun updateAreaColor(pickedColor: GameColor) {
        currentArea.totalArea.forEach { position -> board.setColor(position, pickedColor) }
    }

    private fun addValidPositions(){
        getFringeSurroundingPositions()
            .filter { position -> isValid(position) }
            .forEach { position -> currentArea.addPosition(position) }
    }

    private fun getFringeSurroundingPositions(): List<Position> {
        val positions = mutableListOf<Position>()
        currentArea.fringe.forEach { position -> positions.addAll(position.getSurroundingPositions()) }
        return positions
    }

    private fun isValid(pos: Position) =
        !currentArea.hasPosition(pos) && !enemyArea.hasPosition(pos) && board.hasPosition(pos)

    private fun swapAreas() { currentArea = enemyArea.also { enemyArea = currentArea } }
}