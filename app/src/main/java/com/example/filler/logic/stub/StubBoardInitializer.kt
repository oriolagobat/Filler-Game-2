package com.example.filler.logic.stub

import com.example.filler.constants.GameColor
import com.example.filler.logic.board.BoardImpl
import java.util.*

class StubBoardInitializer(private val board: BoardImpl) {
    private val boardColors = listOf(
        GameColor.ORANGE, GameColor.PURPLE, GameColor.GREEN,
        GameColor.PURPLE, GameColor.ORANGE, GameColor.YELLOW,
        GameColor.YELLOW, GameColor.GREEN, GameColor.PURPLE)

    fun initBoard() {
        for ((index, color) in boardColors.withIndex())
            board.setColor(board.indexToPosition(index), color)
    }
}