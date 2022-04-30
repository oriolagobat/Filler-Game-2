package com.example.filler.logic.game

import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameColor
import com.example.filler.constants.GameState
import com.example.filler.logic.GameImpl
import com.example.filler.logic.GameSettings
import com.example.filler.logic.interfaces.Game
import org.junit.Assert.*
import org.junit.Test

class InitGameTest {
    private val settings = GameSettings(3, 3, Difficulty.EASY)
    private val game: Game = GameImpl(settings)
    private val response = game.initGame()
    private val board = response.board
    private val p1HomeColor = board.getColor(board.getP1Home())
    private val p2HomeColor = board.getColor(board.getP2Home())
    private val selector = response.selector

    @Test
    fun `Response's state is player 1 turn`() {
        assertEquals(GameState.P1_TURN, response.state)
    }

    @Test
    fun `Response's board size is 3x3`() {
        val board = response.board
        assertEquals(3, board.width)
    }

    @Test
    fun `Response's selector selected colors corresponed to p1 and p2 initial colors`() {
        assertEquals(2, selector.getSelectedColors().count())
        assertTrue(
            selector.getSelectedColors().contains(p1HomeColor) &&
                    selector.getSelectedColors().contains(p2HomeColor)
        )
    }

    @Test
    fun `Response's selector available colors only contain remaining color`() {
        assertEquals(1, selector.getAvailableColors().count())
        assertFalse(
            selector.getAvailableColors().contains(p1HomeColor) ||
                    selector.getAvailableColors().contains(p2HomeColor)
        )
    }
}