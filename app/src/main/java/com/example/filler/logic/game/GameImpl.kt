package com.example.filler.logic.game

import com.example.filler.constants.GameColor
import com.example.filler.constants.GameConstants
import com.example.filler.constants.GameState
import com.example.filler.logic.ai.AIColorGeneratorFactoryImpl
import com.example.filler.logic.ai.AIGeneratorSettings
import com.example.filler.logic.board.Board
import com.example.filler.logic.board.BoardColorInitializer
import com.example.filler.logic.board.BoardImpl
import com.example.filler.logic.colors.ColorSelector
import com.example.filler.logic.colors.ColorSelectorImpl
import com.example.filler.logic.player.AreaExpander
import com.example.filler.logic.player.AreaExpanderImpl
import com.example.filler.logic.player.Player
import com.example.filler.logic.player.PlayerAreaImpl

class GameImpl(private val settings: GameSettings) : Game {
    private val availableColors: List<GameColor> = GameColor.values().toList().take(settings.nColors)
    private val selector: ColorSelector = ColorSelectorImpl(availableColors)
    private val board: Board = BoardImpl(settings.boardSize)
    private var state = GameState.INITIALIZING
    private var round = 0
    private lateinit var smartColorGenerator: Generator
    private lateinit var areaExpander: AreaExpander
    private lateinit var checker: Checker
    private lateinit var player1: Player
    private lateinit var player2: Player

    override fun initGame(): GameResponse {
        fillBoard()
        initPlayerLogic()
        initStateLogic()
        return getGameInfo()
    }

    private fun fillBoard() {
        val boardInitializer = BoardColorInitializer(availableColors, board)
        boardInitializer.initialize()
    }

    private fun initPlayerLogic() {
        assignAreas()
        selectInitialColors()
        initP2AI()
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

    private fun initP2AI() {
        val settingsForAI = AIGeneratorSettings(board, player2.area, availableColors)
        smartColorGenerator = AIColorGeneratorFactoryImpl()
            .makeGenerator(this.settings.difficulty, settingsForAI)
    }

    private fun initStateLogic() {
        areaExpander = AreaExpanderImpl(player1, player2, board)
        checker = CheckerImpl(player1, player2, board)
        state = GameState.P1_TURN
    }

    override fun getGameInfo(): GameResponse {
        return GameResponse(round, board, selector, state)
    }

    override fun pickPlayerColor(player: Player, color: GameColor): GameResponse {
        applyColorPick(color, player)
        return getGameInfo()
    }

    override fun pickP2ColorThroughAI(): GameResponse {
        val color = smartColorGenerator.generate()
        applyColorPick(color, player2)
        return getGameInfo()
    }

    private fun applyColorPick(color: GameColor, player: Player) {
        areaExpander.updatePlayerArea(player, color)
        checker.updateState(state)
    }
}