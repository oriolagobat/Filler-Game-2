package com.example.filler.logic.game

import com.example.filler.constants.logic.GameColor
import com.example.filler.constants.logic.GameState
import com.example.filler.log.Logger
import com.example.filler.logic.board.Board
import com.example.filler.logic.colors.ColorSelector
import com.example.filler.logic.colors.Generator
import com.example.filler.logic.colors.RandomColorGenerator
import com.example.filler.logic.player.Player
import com.example.filler.logic.score.ScoreCalculator
import com.example.filler.persistence.database.GameSummary

class GameImpl(
    private val scoreCalculator: ScoreCalculator,
    private val smartColorGenerator: Generator,
    private val selector: ColorSelector,
    private val board: Board,
    private val gameData: GameData,
    private val player1: Player,
    private val player2: Player,
    private val stats: GameStats
) : Game {

    override fun pickColorThroughAI(): GameResponse {
        val color = smartColorGenerator.generate()
        return pickColorManually(color)
    }

    override fun pickRandomColor(): GameResponse {
        val color = RandomColorGenerator(selector.getAvailableColors()).generate()
        return pickColorManually(color)
    }

    override fun pickColorManually(color: GameColor): GameResponse {
        setRound()
        Logger.logInfo("${gameData.currentPlayer.id} picked color $color")
        updateBoard(color)
        setNextState()
        Logger.logDebug("Game state changed to: ${gameData.state}, sending response...")
        return getGameResponse()
    }

    private fun setRound() {
        if (gameData.state == GameState.P1_TURN) {
            gameData.round++
            Logger.logInfo("Round ${gameData.round}")
        }
    }

    private fun updateBoard(color: GameColor) {
        selector.select(color)
        calculateScore(color)
    }

    private fun calculateScore(color: GameColor) {
        scoreCalculator.updateAreas(color)
        gameData.currentPlayer.updateScore()
        Logger.logInfo("${gameData.currentPlayer.id} new score is: ${gameData.currentPlayer.score}")
    }

    private fun setNextState() = if (!gameFinished()) swapTurns() else finishGame()

    private fun gameFinished() = player1.score + player2.score == board.getNumCells()

    private fun swapTurns() {
        gameData.apply {
            state = if (state == GameState.P1_TURN) GameState.P2_TURN else GameState.P1_TURN
            currentPlayer = enemyPlayer.also { enemyPlayer = currentPlayer }
        }
    }

    private fun finishGame() {
        setFinishState()
        setFinishStats()
    }

    private fun setFinishState() = if (!isDraw()) setWinner() else gameData.state = GameState.DRAW

    private fun setFinishStats() {
        stats.p1Score = player1.score
        stats.p2Score = player2.score
        stats.setP1ConqueredAreaPercent(player1.score, board.getNumCells())
        stats.outcome = gameData.state.toString()
        stats.setEndDate()
    }


    private fun isDraw() = player1.score == player2.score

    private fun setWinner() {
        gameData.state = if (player1.score > player2.score) GameState.P1_WON else GameState.P2_WON
    }

    override fun getGameResponse() =
        GameResponse(gameData.state, gameData.round, player1.score, player2.score, board, selector)

    override fun getGameSummary(): GameSummary {
        return stats.makeSummary()
    }
}
