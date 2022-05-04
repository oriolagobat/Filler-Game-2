package com.example.filler.logic.colors

import com.example.filler.constants.logic.GameColor

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