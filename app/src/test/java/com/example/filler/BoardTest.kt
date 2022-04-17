package com.example.filler

import com.example.filler.constants.Colors
import com.example.filler.logic.Board
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before



class BoardTest {
    private val boardSize = 3
    private lateinit var board: Board
    private lateinit var colors: Array<Colors>

    @Before
    fun initBoard() {
        colors = arrayOf(Colors.RED, Colors.GREEN, Colors.BLUE)
        board = Board(boardSize, colors)
    }

    @Test
    fun playerStartingColors() {
        val p1Color = board.getP1Color()
        val p2Color = board.getP2Color()
        assertTrue(p1Color in colors)
        assertTrue(p2Color in colors)
    }

    @Test
    fun boardGetColors() {
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                val color = board.getColor(i, j)
                assertTrue(color in colors)
            }
        }
    }

    @Test
    fun getRandomColor() {
        val size = 20
        val randomColors = Array(size) { board.getRandomColor() }
        for (color in randomColors) {
            assertTrue(color in colors)
        }
    }
}