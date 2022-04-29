package com.example.filler.logic

import com.example.filler.constants.GameColor
import com.example.filler.logic.interfaces.Generator

class RandomColorGenerator(
    private val colors: List<GameColor>
) : Generator {

    override fun generate(): GameColor {
        return getRandomColor()
    }

    private fun getRandomColor(): GameColor {
        return colors[(colors.indices).random()]
    }
}