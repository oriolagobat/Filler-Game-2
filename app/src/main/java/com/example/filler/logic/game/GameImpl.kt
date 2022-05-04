package com.example.filler.logic.game

import com.example.filler.constants.logic.GameColor
import com.example.filler.constants.logic.GameState
import com.example.filler.log.Logger
import com.example.filler.logic.board.Board
import com.example.filler.logic.colors.ColorSelector
import com.example.filler.logic.colors.Generator
import com.example.filler.logic.player.Player
import com.example.filler.logic.score.ScoreCalculator

class GameImpl(
    private val scoreCalculator: ScoreCalculator,
    private val smartColorGenerator: Generator,
    private val selector: ColorSelector,
    private val board: Board,
    private val gameData: GameData,
    private val player1: Player,
    private val player2: Player
) : Game {

    override fun pickColorManually(color: GameColor): GameResponse {
        Logger.logI("${gameData.currentPlayer.id} picked color $color")
        selector.select(color)
        calculateScore(color)
        setNextState()
        Logger.logD("Game state changd to: ${gameData.state}, sending response...")
        return getGameResponse()
    }

    private fun calculateScore(color: GameColor) {
        scoreCalculator.updateAreas(color)
        gameData.currentPlayer.updateScore()
        Logger.logI("${gameData.currentPlayer.id} new score is: ${gameData.currentPlayer.score}")
    }

    private fun setNextState() = if (!gameFinished()) swapTurns() else setFinishState()

    private fun gameFinished() = player1.score + player2.score == board.getNumCells()

    private fun swapTurns() {
        gameData.apply {
            state = if (state == GameState.P1_TURN) GameState.P2_TURN else GameState.P1_TURN
            currentPlayer = enemyPlayer.also { enemyPlayer = currentPlayer }
        }
    }

    private fun setFinishState() = if (!isDraw()) setWinner() else gameData.state = GameState.DRAW

    private fun isDraw() = player1.score == player2.score

    private fun setWinner() {
        gameData.state = if (player1.score > player2.score) GameState.P1_WON else GameState.P2_WON
    }

    override fun pickColorThroughAI(): GameResponse {
        val color = smartColorGenerator.generate()
        return pickColorManually(color)
    }

    override fun getGameResponse() =
        GameResponse(gameData.state, gameData.round, player1.score, player2.score, board, selector)
}