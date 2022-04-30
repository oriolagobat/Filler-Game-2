package com.example.filler.logic

import com.example.filler.constants.GameColor
import com.example.filler.constants.GameState
import com.example.filler.logic.interfaces.Board
import com.example.filler.logic.interfaces.ColorSelector
import com.example.filler.logic.interfaces.Game
import com.example.filler.logic.interfaces.Generator

class GameImpl(private val settings: GameSettings) : Game {

    private val board: Board = BoardImpl(settings.boardSize)
    private val round = 0
    private val state = GameState.INITIALISING
    private lateinit var randomColorGenerator: Generator
    private lateinit var AIColorGenerator: Generator
    private lateinit var colors: List<GameColor>
    private lateinit var selector: ColorSelector
    private lateinit var player1: Player
    private lateinit var player2: Player

    override fun initGame(): GameResponse {
        defineColors()
        fillBoard()
        //TODO: També s'ha d'iniciar el selector.
        initPlayers()
        return generateResponse()
    }

    private fun defineColors() {
        colors = GameColor.values().toList().take(settings.nColors)
    }

    private fun fillBoard() {
        TODO("Not yet implemented")
    }

    private fun initPlayers() {
        TODO("Not yet implemented")
    }

    private fun generateResponse(): GameResponse {
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