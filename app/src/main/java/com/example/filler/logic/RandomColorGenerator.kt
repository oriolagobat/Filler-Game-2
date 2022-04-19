package com.example.filler.logic

import com.example.filler.constants.GameColor

class RandomColorGenerator(
    private val colors: Array<GameColor>,
) : Generator {

    override fun generate(): GameColor {
        return getRandomColor()
    }

    private fun getRandomColor(): GameColor {
        return colors[(colors.indices).random()]
    }
}