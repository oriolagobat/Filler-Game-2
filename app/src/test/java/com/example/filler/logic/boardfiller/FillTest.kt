package com.example.filler.logic.boardfiller

import com.example.filler.constants.GameColor
import com.example.filler.logic.board.BoardColorInitializer
import com.example.filler.logic.board.BoardImpl
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class FillTest {
    private val colors = listOf(GameColor.GREEN, GameColor.PURPLE, GameColor.YELLOW)
    private val board = BoardImpl(4)
    private val boardColorFiller = BoardColorInitializer(colors, board)

    @Before
    fun setUp() {

    }

    @Test
    fun fill() {
        boardColorFiller.initialize()
        val p1HomeColor = board.getColor(board.getP1Home())
        val p2HomeColor = board.getColor(board.getP2Home())
        assertNotEquals(p1HomeColor, p2HomeColor)
    }
}