package com.example.filler.logic.playerarea

import com.example.filler.logic.board.BoardImpl
import com.example.filler.logic.player.PlayerAreaImpl
import com.example.filler.logic.board.Position
import com.example.filler.logic.board.Board
import com.example.filler.logic.player.PlayerArea
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class PlayerAreaImplTest {
    private lateinit var board: Board
    private lateinit var playerArea: PlayerArea
    private lateinit var p1HomePosition: Position

    @Before
    fun setUp(){
        board = BoardImpl(3)
        p1HomePosition = board.getP1Home()
        playerArea = PlayerAreaImpl(p1HomePosition, board)
    }

    @Test
    fun `Fringe returned when no cell added has only p1 home position`() {
        val fringe = mutableSetOf(p1HomePosition)
        assertEquals(fringe, playerArea.fringe)
    }

    @Test
    fun `Area returned when no cell added has only p1 home position`() {
        val area = mutableSetOf(p1HomePosition)
        assertEquals(area, playerArea.totalArea)
    }

    @Test
    fun `Area returned when a cell has been added is correct`() {
        val newPosition = Position(0, board.getNumCols() - 2)
        playerArea.addPosition(newPosition)
        val area = mutableSetOf(p1HomePosition, newPosition)
        assertEquals(area, playerArea.totalArea)
    }

    @Test
    fun `Fringe returned when a cell has been added is correct`() {
        val newPosition = Position(0, board.getNumCols() - 2)
        playerArea.addPosition(newPosition)
        val fringe = mutableSetOf(p1HomePosition, newPosition)
        assertEquals(fringe, playerArea.fringe)
    }

    @Test
    fun `Home position is no longer in fringe when surrounded by area positions`() {
        val aboveHomePosition = Position(board.getNumCols() - 2, 0)
        val rightOfHomePosition = Position(board.getNumCols() - 1, 1)
        playerArea.addPosition(aboveHomePosition)
        playerArea.addPosition(rightOfHomePosition)
        assertFalse(playerArea.fringe.contains(p1HomePosition))
    }

    @Test
    fun `Returns true when asking if area has an added position`() {
        val newPosition = Position(1, 1)
        playerArea.addPosition(newPosition)
        assertTrue(playerArea.hasPosition(newPosition))
    }
}