package com.example.filler

import com.example.filler.constants.Colors
import com.example.filler.logic.Board
import com.example.filler.logic.Filler
import com.example.filler.logic.Position
import com.example.filler.logic.RandomFiller
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class RandomFillerTest {

    private val boardSize = 3
    private lateinit var filler: Filler
    private lateinit var colors: Array<Colors>

    @Before
    fun initBoard() {
        colors = arrayOf(Colors.RED, Colors.GREEN, Colors.BLUE)
        filler = RandomFiller(boardSize, colors)
    }

    @Test
    fun fillReturnsColoredCells() {
        val board = filler.fill()
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                assertTrue(colors.contains(board[row][col].color))
            }
        }
    }

    @Test
    fun getRandomColor() {
        val size = 20
        val randomColors = Array(size) { filler.getRandomColor() }
        for (color in randomColors) {
            assertTrue(color in colors)
        }
    }
}