package com.example.filler.logic.stub

import com.example.filler.constants.logic.GameColor
import com.example.filler.logic.board.BoardImpl

class StubBoardInitializer(private val board: BoardImpl) {
    val boardColors = listOf(
        GameColor.ORANGE, GameColor.YELLOW, GameColor.GREEN,
        GameColor.PURPLE, GameColor.ORANGE, GameColor.YELLOW,
        GameColor.ORANGE, GameColor.GREEN, GameColor.PURPLE
    )

    fun initBoard() {
        for ((index, color) in boardColors.withIndex())
            board.setColor(board.indexToPosition(index), color)
    }
}
