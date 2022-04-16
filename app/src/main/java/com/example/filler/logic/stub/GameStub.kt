package com.example.filler.logic.stub

import com.example.filler.constants.Colors
import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameState
import com.example.filler.logic.Cell
import com.example.filler.logic.GameInt
import com.example.filler.logic.GameResponse

class GameStub(boardWidth: Int, numColors: Int, difficulty: Difficulty) : GameInt {

    private val response = GameResponse(
        0,
        arrayOf(
            arrayOf(Colors.RED, Colors.GREEN, Colors.BLUE),
            arrayOf(Colors.GREEN, Colors.GREEN, Colors.RED),
            arrayOf(Colors.RED, Colors.BLUE, Colors.BLUE)
        ),
        arrayOf(Pair(Colors.RED, true), Pair(Colors.GREEN, false), Pair(Colors.BLUE, true)),
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