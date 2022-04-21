package com.example.filler.logic.stub

import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameColor
import com.example.filler.constants.GameState
import com.example.filler.logic.Game
import com.example.filler.logic.GameResponse

class GameStub(boardWidth: Int, numColors: Int, difficulty: Difficulty) : Game {

    private val response = GameResponse(
        0,
        arrayOf(
            arrayOf(GameColor.CYAN, GameColor.GREEN, GameColor.BLUE),
            arrayOf(GameColor.GREEN, GameColor.GREEN, GameColor.BLACK),
            arrayOf(GameColor.YELLOW, GameColor.BLUE, GameColor.BLUE)
        ),
        arrayOf(
            Pair(GameColor.YELLOW, true),
            Pair(GameColor.GREEN, false),
            Pair(GameColor.BLUE, true)
        ),
        GameState.PLAYING
    )

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