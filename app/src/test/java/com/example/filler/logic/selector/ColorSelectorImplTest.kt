package com.example.filler.logic.selector

import com.example.filler.constants.GameColor
import com.example.filler.logic.colors.ColorSelectorImpl
import com.example.filler.logic.colors.ColorSelector
import org.junit.Assert.*
import org.junit.Test

class ColorSelectorImplTest {
    private val inputColors = listOf(GameColor.YELLOW, GameColor.PURPLE, GameColor.GREEN)
    private val selector: ColorSelector = ColorSelectorImpl(inputColors)

    @Test
    fun `Available colors should be all colors`(){
        assertEquals(inputColors, selector.getAvailableColors())
    }

    @Test
    fun `Selecting a color should remove it from available colors`(){
        val selectedColor = GameColor.GREEN
        selector.select(GameColor.GREEN)
        assertFalse(selector.getAvailableColors().contains(selectedColor))
    }

    @Test
    fun `A selected color should appear on selected colors`(){
        val selectedColor = GameColor.GREEN
        selector.select(GameColor.GREEN)
        assertTrue(selector.getSelectedColors().contains(selectedColor))
    }

    @Test
    fun `Selecting three colors should remove the first one from selected colors`(){
        val firstSelectedColor = GameColor.GREEN
        selector.select(GameColor.GREEN)
        selector.select(GameColor.PURPLE)
        selector.select(GameColor.YELLOW)
        assertFalse(selector.getSelectedColors().contains(firstSelectedColor))
    }

    @Test
    fun `Total amount of colors should be the sum of available of selected colors`(){
        selector.select(GameColor.GREEN)
        val total = selector.getAvailableColors().count() + selector.getSelectedColors().count()
        assertEquals(total, selector.getTotalAmountOfColors())
    }
}