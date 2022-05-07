package com.example.filler.logic.boardfiller

import com.example.filler.constants.logic.GameColor
import com.example.filler.logic.board.BoardColorInitializer
import com.example.filler.logic.board.BoardImpl
import com.example.filler.logic.board.Position
import com.example.filler.logic.colors.RandomColorGenerator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class FillTest {
    private val colors = listOf(GameColor.GREEN, GameColor.PURPLE, GameColor.YELLOW)
    private val board = BoardImpl(3)
    private val generator = RandomColorGenerator(colors)
    private val boardColorFiller = BoardColorInitializer(colors, board, generator)

    @Before
    fun setUp() {
        boardColorFiller.start()
    }

    @Test
    fun `Home positions have different colors`() {
        val p1HomeColor = board.getColor(board.getP1Home())
        val p2HomeColor = board.getColor(board.getP2Home())
        assertNotEquals(p1HomeColor, p2HomeColor)
    }

    // Test if there are no two equal neighbor colors
    @Test
    fun `Neighbor positions have different colors`() {
        boardColorFiller.start()
        val res1 = (0 until board.getNumCells()).toMutableList()
            .map { index -> board.indexToPosition(index) }
            .any { position ->
                position.getSurroundingPositions().any { neighbor ->
                    board.hasPosition(neighbor) && shareColors(position, neighbor)
                }
            }
        assertFalse(res1)
    }

    private fun shareColors(position: Position, neighbor: Position): Boolean =
        board.getColor(position) == board.getColor(neighbor)

}