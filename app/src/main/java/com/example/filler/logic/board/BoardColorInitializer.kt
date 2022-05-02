package com.example.filler.logic.board

import com.example.filler.constants.GameColor
import com.example.filler.logic.colors.RandomColorGenerator
import com.example.filler.logic.colors.Generator

class BoardColorInitializer(
    private val colors: List<GameColor>,
    private val board: Board,
    private var randGenerator: Generator
) {

    fun start() {
        fillWithRandomColors()
        if (playersShareStartingColor())
            updateP2Color()
    }

    private fun fillWithRandomColors() {
        for (i in 0 until board.getNumCells())
            addColor(board.indexToPosition(i))
    }

    private fun addColor(position: Position) {
        val color = getValidColor(position)
        board.setColor(position, color)
    }

    private fun getValidColor(position: Position): GameColor {
        var color = randGenerator.generate()
        while (isNotValid(color, position)) color = randGenerator.generate()
        return color
    }

    private fun isNotValid(color: GameColor, position: Position): Boolean {
        return position.getSurroundingPositions().filter { board.hasPosition(it) }
            .any { board.getColor(it) == color }
    }

    private fun playersShareStartingColor() =
        board.getColor(board.getP1Home()) == board.getColor(board.getP2Home())

    private fun updateP2Color() {
        randGenerator = RandomColorGenerator(getAllButP1Colors())
        board.setColor(board.getP2Home(), randGenerator.generate())
    }

    private fun getAllButP1Colors(): List<GameColor> {
        return colors
            .filter { it != board.getColor(board.getP1Home()) }
            .toList()
    }
}