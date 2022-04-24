package com.example.filler.logic

import com.example.filler.constants.GameColor
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.collections.ArrayList


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
    fun `Initial player positions correspond to lower left and upper righ positions`() {
        val p1Home = Position(boardSize - 1, 0)
        val p2Home = Position(0, boardSize - 1)
        assertEquals(p1Home, board.getP1Home())
        assertEquals(p2Home, board.getP2Home())
    }

    @Test
    fun `Board returns setted color when asking for any position's color`() {
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                val color = board.getColor(Position(row, col))
                assertEquals(color, colorArray[row * boardSize + col])
            }
        }
    }

    @Test
    fun `Surrounding cells' position of a given position are correct`() {
        val pos = Position(1, 1)
        val surroundingPositions = ArrayList<GameColor>()
        surroundingPositions.add(board.getColor(Position(0, 1)))
        surroundingPositions.add(board.getColor(Position(2, 1)))
        surroundingPositions.add(board.getColor(Position(1, 0)))
        surroundingPositions.add(board.getColor(Position(1, 2)))
        assertEquals(board.getSurroundingColors(pos), surroundingPositions)
    }

    @Test
    fun `Board array retured equals colorArray`() {
        assertArrayEquals(board.getBoardAsColorArray(), colorArray)
    }
}