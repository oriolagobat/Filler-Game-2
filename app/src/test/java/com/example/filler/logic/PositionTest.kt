package com.example.filler.logic

import com.example.filler.constants.GameColor
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class PositionTest {

    private val position = Position(1, 1)

    @Test
    fun getSurroundingPositions() {
        val pos = Position(1, 1)
        val surroundingPositions = mutableListOf<Position>()
        surroundingPositions.add(Position(0, 1))
        surroundingPositions.add(Position(2, 1))
        surroundingPositions.add(Position(1, 0))
        surroundingPositions.add(Position(1, 2))
        assertEquals(pos.getSurroundingPositions(), surroundingPositions)
    }

    @Test
    fun getAbovePosition() {
        val abovePosition = Position(0, 1)
        assertEquals(position.getAbovePosition(), abovePosition)
    }

    @Test
    fun getBelowPosition() {
        val belowPosition = Position(2, 1)
        assertEquals(position.getBelowPosition(), belowPosition)
    }

    @Test
    fun getLeftPosition() {
        val leftPosition = Position(1, 0)
        assertEquals(position.getLeftPosition(), leftPosition)
    }

    @Test
    fun getRightPosition() {
        val rightPosition = Position(1, 2)
        assertEquals(position.getRightPosition(), rightPosition)
    }

    @Test
    fun getRow() {
        assertEquals(position.row, 1)
    }

    @Test
    fun getCol() {
        assertEquals(position.col, 1)
    }
}