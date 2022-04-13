package com.example.filler.logic.stub

import com.example.filler.constants.Colors
import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameState
import com.example.filler.logic.Cell
import com.example.filler.logic.GameInt
import com.example.filler.logic.GameResponse

class GameStub(boardWidth: Int, numColors: Int, difficulty: Difficulty) : GameInt {

    private val redCell = Cell(Colors.RED)
    private val greenCell = Cell(Colors.GREEN)
    private val blueCell = Cell(Colors.BLUE)

    private val response = GameResponse(
        0,
        arrayOf(
            arrayOf(redCell, greenCell, redCell),
            arrayOf(greenCell, blueCell, greenCell),
            arrayOf(blueCell, redCell, redCell)
        ),
        arrayOf(Pair(Colors.RED, true), Pair(Colors.GREEN, true), Pair(Colors.BLUE, false)),
        GameState.PLAYING
    )

    override fun initGame(): GameResponse {
        return response
    }

    override fun startGame() {
        print("Game started")
    }

    override fun pickColor(color: Colors): GameResponse {
        return response
    }
}