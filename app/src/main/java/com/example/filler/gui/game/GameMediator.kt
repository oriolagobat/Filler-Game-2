package com.example.filler.gui.game

import android.widget.GridView
import android.widget.TextView
import androidx.activity.viewModels
import com.example.filler.FillerApplication
import com.example.filler.constants.logic.GameColor
import com.example.filler.constants.logic.GameState
import com.example.filler.gui.game.adapters.BoardAdapter
import com.example.filler.gui.game.adapters.SelectorAdapter
import com.example.filler.gui.game.fragments.GameFragment
import com.example.filler.logic.game.Game
import com.example.filler.logic.game.GameFactoryImpl
import com.example.filler.logic.game.GameResponse
import com.example.filler.logic.game.GameSettings
import com.example.filler.persistence.database.GameSummaryViewModel
import com.example.filler.persistence.database.GameSummaryViewModelFactory
import com.example.filler.timer.TimerFactoryImpl

class GameMediator(
    var gameFragment: GameFragment,
    gameSettings: GameSettings,
    var board: GridView,
    var selector: GridView,
    timerView: TextView,
) {

    private val game: Game = GameFactoryImpl(gameSettings).makeGame()
    private var gameState: GameResponse = game.getGameResponse()
    private var boardContent: Array<GameColor> = gameState.board.toArray()
    private var selectorContent: Array<Pair<GameColor, Boolean>> = gameState.selector.toArray()
    val timer = TimerFactoryImpl(this, timerView, gameSettings).createTimer()

    fun start() {
        setUpGameBoard()
        setUpSelector()
        timer.init()
        newRound()
    }

    private fun setUpGameBoard() {
        val boardSize = gameState.board.getNumCols()
        board.numColumns = boardSize
        board.adapter = BoardAdapter(gameFragment.requireContext(), boardContent, board)
    }

    private fun setUpSelector() {
        val selectorNum = gameState.selector.getTotalAmountOfColors()
        selector.numColumns = selectorNum
        selector.adapter = SelectorAdapter(gameFragment.requireContext(), selectorContent, selector)
    }

    private fun newRound() {
        when (gameState.state) {
            GameState.P1_TURN -> playerTurn()
            GameState.P2_TURN -> aiTurn()
            else -> throw IllegalStateException("Game state is not valid")
        }
    }

    private fun playerTurn() {
        timer.start()
        val colorsSelector = getArrayColors(selectorContent)
        selector.setOnItemClickListener { _, _, position, _ ->
            val color = colorsSelector[position]
            if (colorUnClickable(selectorContent, color))
                return@setOnItemClickListener
            gameState = game.pickColorManually(color)
            timer.cancel()
            updateGame()
        }
    }

    fun playerTurnByTimeout() {
        gameState = game.pickRandomColor()
        updateGame()
    }

    private fun aiTurn() {
        gameState = game.pickColorThroughAI()
        updateGame()
    }

    private fun updateGame() = if (gameFinished(gameState)) finishGame() else setUpNextRound()

    private fun finishGame() {
        gameFragment.startResultsActivity(gameState)
        timer.finish()
    }

    private fun setUpNextRound() {
        updateBoard()
        updateSelector()
        newRound()
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