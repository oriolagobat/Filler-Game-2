package com.example.filler.logic.board

import com.example.filler.constants.GameColor
import com.example.filler.logic.BoardImpl
import com.example.filler.logic.Position
import com.example.filler.logic.interfaces.Board
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class BoardTest {
    private val boardSize = 3
    private lateinit var board: Board
    private val colorArray = arrayOf(
        GameColor.ORANGE,
        GameColor.ORANGE,
        GameColor.ORANGE,
        GameColor.YELLOW,
        GameColor.YELLOW,
        GameColor.YELLOW,
        GameColor.GREEN,
        GameColor.GREEN,
        GameColor.GREEN
    )

    @Before
    fun init() {
        board = BoardImpl(boardSize)
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                board.setColor(Position(row, col), colorArray[row * boardSize + col])
            }
        }
    }

    @Test
    fun `Initial player positions correspond to lower left and upper right positions`() {
        val p1Home = Position(boardSize - 1, 0)
        val p2Home = Position(0, boardSize - 1)
        assertEquals(p1Home, board.getP1Home())
        assertEquals(p2Home, board.getP2Home())
    }

    @Test
    fun `Board returns set color when asking for any position's color`() {
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                val color = board.getColor(Position(row, col))
                assertEquals(color, colorArray[row * boardSize + col])
            }
        }
    }

    @Test
    fun `Board array returned equals colorArray`() {
        assertArrayEquals(board.toArray(), colorArray)
    }

    @Test
    fun `Returns false when asking if it contains a position outside the board`() {
        val outsidePosition = Position(boardSize, 0)
        assertFalse(board.hasPosition(outsidePosition))
    }

    @Test
    fun `Returns false when asking for a position with negative row or column`() {
        val negativeRowPosition = Position(-1, 0)
        assertFalse(board.hasPosition(negativeRowPosition))
        val negativeColPosition = Position(0, -1)
        assertFalse(board.hasPosition(negativeColPosition))
    }
}