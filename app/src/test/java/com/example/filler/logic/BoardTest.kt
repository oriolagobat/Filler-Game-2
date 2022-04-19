package com.example.filler.logic

import com.example.filler.constants.GameColor
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.collections.ArrayList


class BoardTest {
    private val boardSize = 3
    private lateinit var board: BoardImp

    @Before
    fun init() {
        board = BoardImp(boardSize)
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                board.setColor(Position(i, j), GameColor.YELLOW)
            }
        }
    }

    @Test
    fun `Initial player colors correspond to lower left and upper right corners' colors`() {
        val p1HomeColor = GameColor.RED
        val p2HomeColor = GameColor.BLUE
        board.setColor(Position(boardSize - 1, 0), p1HomeColor)
        board.setColor(Position(0, boardSize - 1), p2HomeColor)
        assertEquals(p1HomeColor, board.getP1HomeColor())
        assertEquals(p2HomeColor, board.getP2HomeColor())
    }

    @Test
    fun `Board returns setted color when asking for any position's color`() {
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                val color = board.getColor(Position(i, j))
                assertEquals(color, GameColor.YELLOW)
            }
        }
    }

    @Test
    fun `Surrounding cells' position of a given position should be correct`() {
        val pos = Position(1, 1)
        val surroundingPositions = ArrayList<GameColor>()
        surroundingPositions.add(board.getColor(Position(0, 1)))
        surroundingPositions.add(board.getColor(Position(2, 1)))
        surroundingPositions.add(board.getColor(Position(1, 0)))
        surroundingPositions.add(board.getColor(Position(1, 2)))
        assertEquals(board.getSurroundingColors(pos), surroundingPositions)
    }
}