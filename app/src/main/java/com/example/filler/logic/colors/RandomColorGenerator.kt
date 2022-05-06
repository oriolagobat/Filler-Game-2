package com.example.filler.logic.colors

import com.example.filler.constants.logic.GameColor
import kotlin.random.Random

class RandomColorGenerator(
    private val colors: List<GameColor>
) : Generator {

    override fun generate(): GameColor {
        return getRandomColor()
    }

    private fun getRandomColor(): GameColor {
        return colors[(colors.indices).random(Random(System.currentTimeMillis()))]
    }
}