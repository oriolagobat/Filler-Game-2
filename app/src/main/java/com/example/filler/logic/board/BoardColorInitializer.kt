package com.example.filler.logic.board

import com.example.filler.constants.GameColor
import com.example.filler.logic.colors.RandomColorGenerator
import com.example.filler.logic.game.Generator

class BoardColorInitializer(
    private val colors: List<GameColor>,
    private val board: Board
) {
    private var randGenerator: Generator = RandomColorGenerator(colors)

    fun initialize() {
        fillWithRandomColors()
        if (playersShareStartingColor())
            updateP2Color()
    }

    private fun fillWithRandomColors() {
        for (i in board.colors.indices)
            board.colors[i] = randGenerator.generate()
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