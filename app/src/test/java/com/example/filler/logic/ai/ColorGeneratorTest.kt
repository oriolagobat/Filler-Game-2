package com.example.filler.logic.ai

import com.example.filler.constants.GameColor
import com.example.filler.constants.GameConstants
import com.example.filler.logic.board.BoardImpl
import com.example.filler.logic.colors.ColorSelector
import com.example.filler.logic.colors.ColorSelectorImpl
import com.example.filler.logic.player.PlayerAreaImpl
import com.example.filler.logic.score.ScoreCalculatorImpl
import com.example.filler.logic.stub.StubBoardInitializer
import com.nhaarman.mockito_kotlin.stub
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ColorGeneratorTest {
    private lateinit var colorGenerator: ColorGenerator
    private val board = BoardImpl(3)
    private lateinit var boardInitializer: StubBoardInitializer
    private lateinit var selector: ColorSelector
    private val p1Area = PlayerAreaImpl(board.getP1Home(), board)
    private val p2Area = PlayerAreaImpl(board.getP2Home(), board)
    private val calculator = ScoreCalculatorImpl(board, p1Area, p2Area)

    @Before
    fun setUp() {
        boardInitializer = StubBoardInitializer(board)
        boardInitializer.initBoard()
        selector = ColorSelectorImpl(boardInitializer.boardColors)
        selector.select(board.getColor(board.getP1Home()))
        selector.select(board.getColor(board.getP2Home()))
        colorGenerator = EasyModeColorGenerator(AIGeneratorSettings(board, p1Area, p2Area, selector))
    }

    @Test
    fun `Colors returned when no moves done are correct`() {
        val colors = colorGenerator.getColorsByGoodness()
        assertEquals(selector.getAvailableColors().count(), colors.count())
        assertEquals(GameColor.YELLOW, colors.first())
    }

    @Test
    fun `Colors returned after 1st move`() {
        selector.select(GameColor.YELLOW)
        calculator.updateAreas(GameColor.YELLOW)
        val colors = colorGenerator.getColorsByGoodness()
        assertEquals(selector.getAvailableColors().count(), colors.count())
        assertEquals(GameColor.ORANGE, colors.first())
        assertEquals(GameColor.PURPLE, colors[colors.count() - 1])
    }
}