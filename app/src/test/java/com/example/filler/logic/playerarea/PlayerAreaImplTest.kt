package com.example.filler.logic.playerarea

import com.example.filler.logic.BoardImpl
import com.example.filler.logic.PlayerAreaImpl
import com.example.filler.logic.Position
import com.example.filler.logic.interfaces.PlayerArea
import org.junit.Assert.*

import org.junit.Test

class PlayerAreaImplTest {
    private val board = BoardImpl(3)
    private val p1HomePosition = board.getP1Home()
    private val playerArea: PlayerArea = PlayerAreaImpl(p1HomePosition, board)

    @Test
    fun `Fringe returned when no cell added has only p1 home position`() {
        val fringe = mutableSetOf(p1HomePosition)
        assertEquals(fringe, playerArea.fringe)
    }

    @Test
    fun `Area returned when no cell added has only p1 home position`() {
        val area = mutableSetOf(p1HomePosition)
        assertEquals(area, playerArea.area)
    }

    @Test
    fun `Area returned when a cell has been added is correct`() {
        val newPosition = Position(0, board.size - 2)
        playerArea.addPosition(newPosition)
        val area = mutableSetOf(p1HomePosition, newPosition)
        assertEquals(area, playerArea.area)
    }

    //TODO: test fringe
    @Test
    fun `Fringe returned when a cell has been added is correct`() {
        val newPosition = Position(0, board.size - 2)
        playerArea.addPosition(newPosition)
        val fringe = mutableSetOf(p1HomePosition, newPosition)
        assertEquals(fringe, playerArea.fringe)
    }

    @Test
    fun `Home position is no longer in fringe when surrounded by area positions`() {
        val aboveHomePosition = Position(board.size - 2, 0)
        val rightOfHomePosition = Position(board.size - 1, 1)
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