package com.example.filler.logic

import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameColor
import com.example.filler.logic.interfaces.ColorSelector
import com.example.filler.logic.stub.GameStub9x9
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class ColorSelectorImplTest {
    private val colorArray = arrayListOf(
        GameColor.ORANGE,
        GameColor.ORANGE,
        GameColor.ORANGE,
        GameColor.YELLOW,
        GameColor.YELLOW,
        GameColor.YELLOW,
        GameColor.GREEN,
        GameColor.GREEN,
        GameColor.GREEN
    )
    private val colorSelector: ColorSelector = ColorSelectorImpl(colorArray)

    @Test
    fun toArray() {
        //TODO: test
    }
}