package com.example.filler.logic

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class PlayerAreaImplTest {
    private val boardSize = 3
    private val p1HomePosition = Position(boardSize - 1, 0)
    private val playerArea: PlayerArea = PlayerAreaImpl(p1HomePosition, boardSize)

    @Before
    fun setUp() {

    }

    @Test
    fun `Fringe returned when no cell added has only p1 home position`() {
        val fringe = mutableListOf(p1HomePosition)
        assertEquals(fringe, playerArea.fringe)
    }

    @Test
    fun `Area returned when no cell added has only p1 home position`() {
        val area = mutableListOf(p1HomePosition)
        assertEquals(area, playerArea.area)
    }

    @Test
    fun `Area returned when a cell has been added is correct`() {
        val newPosition = Position(0, boardSize - 2)
        playerArea.addPosition(newPosition)
        val area = mutableListOf(p1HomePosition, newPosition)
        assertEquals(area, playerArea.area)
    }

    //TODO: test fringe
    @Test
    fun `Fringe returned when a cell has been added is correct`() {
        val newPosition = Position(0, boardSize - 2)
        playerArea.addPosition(newPosition)
        val fringe = mutableListOf(p1HomePosition, newPosition)
        assertEquals(fringe, playerArea.fringe)
    }

    @Test
    fun `Home position is no longer in fringe when surronded by area positions`() {
        val aboveHomePosition = Position(boardSize - 2, 0)
        val rightOfHomePosition = Position(boardSize  - 1, 1)
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