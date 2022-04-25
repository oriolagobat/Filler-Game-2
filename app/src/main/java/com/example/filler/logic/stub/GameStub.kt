package com.example.filler.logic.stub

import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameColor
import com.example.filler.constants.GameState
import com.example.filler.logic.*

class GameStub(private val settings: GameSettings) : Game {

    private val boardSize = 3
    private var board: Board = BoardImpl(boardSize)
    private val response = GameResponse(
        0,
        board,
        arrayOf(
            Pair(GameColor.ORANGE, true),
            Pair(GameColor.YELLOW, false),
            Pair(GameColor.GREEN, true),
        ),
        GameState.P1_TURN
    )
    private val colorArray = arrayOf(
        GameColor.PINK,
        GameColor.ORANGE,
        GameColor.YELLOW,
        GameColor.GREEN,
        GameColor.BLUE,
        GameColor.PURPLE,
        GameColor.CYAN,
        GameColor.BLACK,
        GameColor.CYAN
    )

    init {
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                board.setColor(Position(row, col), colorArray[row * boardSize + col])
            }
        }
    }

    override fun initGame(): GameResponse {
        return response
    }

    override fun startGame() {
        print("Game started")
    }

    override fun pickColor(color: GameColor): GameResponse {
        return response
    }
}