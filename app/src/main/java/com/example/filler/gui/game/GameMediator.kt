package com.example.filler.gui.game

import android.widget.GridView
import com.example.filler.constants.GameColor
import com.example.filler.constants.GameState
import com.example.filler.gui.game.adapters.BoardAdapter
import com.example.filler.gui.game.adapters.SelectorAdapter
import com.example.filler.logic.game.Game
import com.example.filler.logic.game.GameFactoryImpl
import com.example.filler.logic.game.GameResponse
import com.example.filler.logic.game.GameSettings

class GameMediator(
    private val context: GUIGame,
    gameSettings: GameSettings,
    private val board: GridView,
    private val selector: GridView
) {
    private val game: Game = GameFactoryImpl(gameSettings).makeGame()
    private var gameState: GameResponse = game.getGameResponse()
    private var boardContent: Array<GameColor> = gameState.board.toArray()
    private var selectorContent: Array<Pair<GameColor, Boolean>> = gameState.selector.toArray()
    fun start() {
        setUpGameBoard()
        setUpSelector()
        newRound()
    }

    private fun setUpGameBoard() {
        val boardSize = gameState.board.getNumCols()
        board.numColumns = boardSize
        board.adapter = BoardAdapter(context, boardContent, board)
    }

    private fun setUpSelector() {
        val selectorNum = gameState.selector.getTotalAmountOfColors()
        selector.numColumns = selectorNum
        selector.adapter = SelectorAdapter(context, selectorContent, selector)
    }

    private fun newRound() {
        when (gameState.state) {
            GameState.P1_TURN -> playerTurn()
            GameState.P2_TURN -> aiTurn()
            else -> throw IllegalStateException("Game state is not valid")
        }
    }

    private fun playerTurn() {
        val colorsSelector = getArrayColors(selectorContent)
        selector.setOnItemClickListener { _, _, position, _ ->
            val color = colorsSelector[position]
            if (colorUnClickable(selectorContent, color))
                return@setOnItemClickListener
            gameState = game.pickColorManually(color)
            updateGame()
        }
    }

    private fun aiTurn() {
        gameState = game.pickColorThroughAI()
        updateGame()
    }

    private fun updateGame() {
        if (gameFinished(gameState)) context.startResultsActivity(gameState) else {
            updateBoard()
            updateSelector()
            newRound()
        }
    }

    private fun updateSelector() {
        selectorContent = gameState.selector.toArray()

        val selectorAdapter = selector.adapter as SelectorAdapter
        selectorAdapter.content = selectorContent
        selectorAdapter.notifyDataSetChanged()
    }

    private fun updateBoard() {
        boardContent = gameState.board.toArray()

        val boardAdapter = board.adapter as BoardAdapter
        boardAdapter.colorsList = boardContent
        boardAdapter.notifyDataSetChanged()
    }

}