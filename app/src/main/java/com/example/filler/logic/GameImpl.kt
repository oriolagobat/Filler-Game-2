package com.example.filler.logic

import com.example.filler.constants.GameColor
import com.example.filler.constants.GameConstants
import com.example.filler.constants.GameState
import com.example.filler.logic.ai.AIColorGeneratorFactoryImpl
import com.example.filler.logic.ai.AIGeneratorSettings
import com.example.filler.logic.interfaces.Board
import com.example.filler.logic.interfaces.ColorSelector
import com.example.filler.logic.interfaces.Game
import com.example.filler.logic.interfaces.Generator

class GameImpl(private val settings: GameSettings) : Game {
    private val availableColors: List<GameColor> = GameColor.values().toList().take(settings.nColors)
    private val selector: ColorSelector = ColorSelectorImpl(availableColors)
    private val board: Board = BoardImpl(settings.boardSize)
    private var state = GameState.INITIALIZING
    private var round = 0
    private lateinit var smartColorGenerator: Generator
    private lateinit var player1: Player
    private lateinit var player2: Player

    override fun initGame(): GameResponse {
        fillBoard()
        initPlayers()
        initAI()
        state = GameState.P1_TURN
        return generateResponse()
    }

    private fun fillBoard() {
        val boardInitializer = BoardColorInitializer(availableColors, board)
        boardInitializer.initialize()
    }

    private fun initPlayers() {
        assignAreas()
        selectInitialColors()
    }

    private fun assignAreas() {
        val player1Area = PlayerAreaImpl(board.getP1Home(), board)
        val player2Area = PlayerAreaImpl(board.getP2Home(), board)
        player1 = Player(GameConstants.INITIAL_SCORE, player1Area)
        player2 = Player(GameConstants.INITIAL_SCORE, player2Area)
    }

    private fun selectInitialColors() {
        selector.select(board.getColor(board.getP1Home()))
        selector.select(board.getColor(board.getP2Home()))
    }

    private fun initAI() {
        val settingsForAI = AIGeneratorSettings(board, player2.area, availableColors)
        smartColorGenerator = AIColorGeneratorFactoryImpl()
            .makeGenerator(this.settings.difficulty, settingsForAI)
    }

    private fun generateResponse(): GameResponse {
        return GameResponse(round, board, selector, state)
    }

    override fun getGameInfo() = generateResponse()

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