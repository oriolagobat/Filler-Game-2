package com.example.filler.logic.ai

import com.example.filler.constants.GameColor
import com.example.filler.logic.board.Position
import com.example.filler.logic.score.ScoreCalculator

open class ColorGenerator(private val settings: AIGeneratorSettings) {
    private lateinit var positions: List<Position>

    fun getColorsByGoodness(): List<GameColor> {
        positions = getCandidatePositions()
        val colors = getAvailableColors()
        return orderByGoodness(colors)
    }

    private fun getCandidatePositions(): List<Position> {
        return settings.p2Area.fringe.flatMap { position -> position.getSurroundingPositions() }
            .filter { position -> isValid(position) }
    }

    private fun getAvailableColors(): List<GameColor> = settings.selector.getAvailableColors()

    private fun orderByGoodness(colors: List<GameColor>): List<GameColor> {
        return colors.map { color -> color to getScoreIncrement(color) }
            .sortedBy { pair -> pair.second }
            .map { pair -> pair.first }
    }

    private fun getScoreIncrement(color: GameColor): Int =
        getNewScore(color) - settings.p2Area.totalArea.count()

    private fun getNewScore(color: GameColor): Int =
        positions.filter { position -> isValid(position) && hasSameColor(position, color) }.count()

    private fun hasSameColor(position: Position, color: GameColor): Boolean =
        settings.board.getColor(position) == color

    private fun isValid(pos: Position) =
        !settings.p2Area.hasPosition(pos) && !settings.p1Area.hasPosition(pos) && settings.board.hasPosition(pos)
}
