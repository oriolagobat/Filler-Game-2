package com.example.filler.logic.score

import com.example.filler.constants.logic.GameColor
import com.example.filler.logic.board.BoardImpl
import com.example.filler.logic.player.PlayerAreaImpl
import com.example.filler.logic.stub.StubBoardInitializer
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ScoreCalculatorImplTest {
    private lateinit var calculator: ScoreCalculator
    private val board = BoardImpl(3)
    private val p1Area = PlayerAreaImpl(board.getP1Home(), board)
    private val p2Area = PlayerAreaImpl(board.getP2Home(), board)

    @Before
    fun setUp() {
        calculator = ScoreCalculatorImpl(board, p1Area, p2Area)
        StubBoardInitializer(board).initBoard()
    }

    @Test
    fun `P1 area size is correct after 1st pick`(){
        calculator.updateAreas(GameColor.PURPLE)
        assertEquals(2, p1Area.totalArea.size)
    }

    @Test
    fun `P2 area size is correct after 1st pick`(){
        calculator.updateAreas(GameColor.ORANGE)
        assertEquals(1, p2Area.totalArea.size)
    }

    @Test
    fun `P1 area size is correct after 2nd pick`(){
        calculator.updateAreas(GameColor.PURPLE)
        calculator.updateAreas(GameColor.ORANGE)
        calculator.updateAreas(GameColor.ORANGE)
        assertEquals(4, p1Area.totalArea.size)
    }
}