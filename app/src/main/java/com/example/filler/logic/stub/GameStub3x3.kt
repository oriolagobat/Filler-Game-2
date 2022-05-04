package com.example.filler.logic.stub

import com.example.filler.constants.logic.GameColor
import com.example.filler.constants.logic.GameState
import com.example.filler.logic.board.Board
import com.example.filler.logic.board.BoardImpl
import com.example.filler.logic.game.GameResponse
import com.example.filler.logic.game.GameSettings
import com.example.filler.logic.colors.ColorSelector
import com.example.filler.logic.colors.ColorSelectorImpl
import com.example.filler.logic.board.Position

class GameStub3x3(private val settings: GameSettings) {
    var round = 0
    val state = GameState.INITIALIZING
    private val boardSize = 3
    private val colorArray = arrayListOf(
        GameColor.ORANGE,
        GameColor.ORANGE,
        GameColor.ORANGE,
        GameColor.YELLOW,
        GameColor.YELLOW,
        GameColor.YELLOW,
        GameColor.GREEN,
        GameColor.GREEN,
        GameColor.GREEN
    )

    val board: Board = BoardImpl(boardSize)
    var selector: ColorSelector = ColorSelectorImpl(colorArray)

    private val response = GameResponse(
        GameState.P1_TURN,
        0,
        0,
        0,
        board,
        selector
    )

    init {
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                board.setColor(Position(row, col), colorArray[row * boardSize + col])
            }
        }
        selector.select(GameColor.ORANGE)
        selector.select(GameColor.GREEN)
    }

    fun initGame(): GameResponse {
        return response
    }

    fun pickP1Color(color: GameColor): GameResponse {
        return response
    }

    fun pickP2Color(color: GameColor): GameResponse {
        return response
    }

    fun pickP2ColorThroughAI(): GameResponse {
        return response
    }

    fun getGameInfo(): GameResponse {
        return response
    }
}