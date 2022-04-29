package com.example.filler.logic

import com.example.filler.constants.GameColor
import com.example.filler.logic.interfaces.Game

class GameImpl(private val settings: GameSettings) : Game {

    override fun initGame(): GameResponse {
        TODO("Not yet implemented")
    }

    override fun pickP1Color(color: GameColor): GameResponse {
        TODO("Not yet implemented")
    }

    override fun pickP2Color(color: GameColor): GameResponse {
        TODO("Not yet implemented")
    }

    override fun pickP2ColorThroughAI(): GameResponse {
        TODO("Not yet implemented")
    }

}