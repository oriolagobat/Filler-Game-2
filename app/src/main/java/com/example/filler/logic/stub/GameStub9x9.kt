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

class GameStub9x9(private val settings: GameSettings) {
    var round = 0
    val state = GameState.INITIALIZING
    private val boardSize = 9
    private val colorArray = arrayListOf(
        GameColor.ORANGE, GameColor.BLACK, GameColor.CYAN, GameColor.YELLOW, GameColor.BLUE, GameColor.BLACK, GameColor.PINK, GameColor.GREEN, GameColor.PURPLE,
        GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK,
        GameColor.CYAN, GameColor.CYAN, GameColor.CYAN, GameColor.CYAN, GameColor.CYAN, GameColor.CYAN, GameColor.CYAN, GameColor.CYAN, GameColor.CYAN,
        GameColor.YELLOW, GameColor.YELLOW, GameColor.YELLOW, GameColor.YELLOW, GameColor.YELLOW, GameColor.YELLOW, GameColor.YELLOW, GameColor.YELLOW, GameColor.YELLOW,
        GameColor.BLUE, GameColor.BLUE, GameColor.BLUE, GameColor.BLUE, GameColor.BLUE, GameColor.BLUE, GameColor.BLUE, GameColor.BLUE, GameColor.BLUE,
        GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK, GameColor.BLACK,
        GameColor.PINK, GameColor.PINK, GameColor.PINK, GameColor.PINK, GameColor.PINK, GameColor.PINK, GameColor.PINK, GameColor.PINK, GameColor.PINK,
        GameColor.GREEN, GameColor.GREEN, GameColor.GREEN, GameColor.GREEN, GameColor.GREEN, GameColor.GREEN, GameColor.GREEN, GameColor.GREEN, GameColor.GREEN,
        GameColor.PURPLE, GameColor.PURPLE, GameColor.PURPLE, GameColor.PURPLE, GameColor.PURPLE, GameColor.PURPLE, GameColor.PURPLE, GameColor.PURPLE, GameColor.PURPLE,
    )

    val board: Board = BoardImpl(boardSize)
    var selector: ColorSelector = ColorSelectorImpl(colorArray)

    private val response = GameResponse(
        GameState.P1_TURN,
        0,
        0,
        0,
        board,
        selector,
    )

    init {
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                board.setColor(Position(row, col), colorArray[row * boardSize + col])
            }
        }
        selector.select(GameColor.PURPLE)
        selector.select(GameColor.ORANGE)
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