package com.example.filler.logic.stub

import com.example.filler.constants.GameColor
import com.example.filler.constants.GameState
import com.example.filler.logic.*
import com.example.filler.logic.interfaces.Board
import com.example.filler.logic.interfaces.ColorSelector
import com.example.filler.logic.interfaces.Game

class GameStub3x3(private val settings: GameSettings) : Game {
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

    private val board: Board = BoardImpl(boardSize)
    private val selector: ColorSelector = ColorSelectorImpl(colorArray)

    private val response = GameResponse(
        0,
        board,
        selector,
        GameState.P1_TURN
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

    override fun initGame(): GameResponse {
        return response
    }

    override fun startGame() {
        print("Game started")
    }

    override fun pickP1Color(color: GameColor): GameResponse {
        return response
    }
}