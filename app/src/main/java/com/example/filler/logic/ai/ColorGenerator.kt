package com.example.filler.logic.ai

import com.example.filler.constants.GameColor
import com.example.filler.logic.board.Position

abstract class ColorGenerator(private val settings: AIGeneratorSettings) {
    private lateinit var positions: List<Position>

    abstract fun chooseColor(colors: List<GameColor>, nUsefulChoices: Int): GameColor

    protected fun getColorsByGoodness(): List<Pair<GameColor, Int>> {
        positions = getCandidatePositions()
        val colors = getAvailableColors()
        return orderByGoodness(colors)
    }

    private fun getCandidatePositions(): List<Position> {
        return settings.p2Area.fringe
            .flatMap { position -> position.getSurroundingPositions() }
            .filter { position -> isValid(position) }
    }

    private fun getAvailableColors(): List<GameColor> = settings.selector.getAvailableColors()

    private fun orderByGoodness(colors: List<GameColor>): List<Pair<GameColor, Int>> {
        return colors
            .map { color -> color to getScoreIncrement(color) }
            .sortedByDescending { (_, score) -> score }
    }

    private fun getScoreIncrement(color: GameColor): Int =
        positions.filter { position -> isValid(position) && hasSameColor(position, color) }.count()

    private fun hasSameColor(position: Position, color: GameColor): Boolean =
        settings.board.getColor(position) == color

    private fun isValid(pos: Position) = !settings.p2Area.hasPosition(pos)
            && !settings.p1Area.hasPosition(pos) && settings.board.hasPosition(pos)

    protected fun getColors(colors: List<Pair<GameColor, Int>>): List<GameColor> =
        colors.map { (color, _) -> color }

    protected fun getUsefulChoices(colors: List<Pair<GameColor, Int>>): Int =
        colors.filter { (_, score) -> score > 0 }.count()
}
