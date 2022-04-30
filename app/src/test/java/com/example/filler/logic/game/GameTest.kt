package com.example.filler.logic.game

import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameState
import org.junit.Assert.assertEquals
import org.junit.Test

class GameTest {

    private val settings = GameSettings(3, 3, Difficulty.EASY)
    private val game: Game = GameImpl(settings)

    @Test
    fun `New game starts on round 0 and state Initializing`() {
        val response = game.getGameInfo()
        assertEquals(0, response.round)
        assertEquals(GameState.INITIALIZING, response.state)
    }

}