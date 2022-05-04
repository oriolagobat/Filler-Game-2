package com.example.filler.logic.boardfiller

import com.example.filler.constants.logic.GameColor
import com.example.filler.logic.board.BoardColorInitializer
import com.example.filler.logic.board.BoardImpl
import com.example.filler.logic.colors.RandomColorGenerator
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class FillTest {
    private val colors = listOf(GameColor.GREEN, GameColor.PURPLE, GameColor.YELLOW)
    private val board = BoardImpl(3)
    private val generator = RandomColorGenerator(colors)
    private val boardColorFiller = BoardColorInitializer(colors, board, generator)

    @Before
    fun setUp() {

    }

    @Test
    fun fill() {
        boardColorFiller.start()
        val p1HomeColor = board.getColor(board.getP1Home())
        val p2HomeColor = board.getColor(board.getP2Home())
        assertNotEquals(p1HomeColor, p2HomeColor)
    }
}