package com.example.filler.logic.game

import com.example.filler.constants.Difficulty
import com.example.filler.logic.GameImpl
import com.example.filler.logic.GameSettings
import com.example.filler.logic.interfaces.Game
import org.junit.Before

import org.junit.Test

class InitGameTest {

    private lateinit var game: Game

    @Before
    fun setUp() {
        val settings = GameSettings(4, 3, Difficulty.EASY)
        game = GameImpl(settings)
    }

    @Test
    fun initGame() {
    }
}