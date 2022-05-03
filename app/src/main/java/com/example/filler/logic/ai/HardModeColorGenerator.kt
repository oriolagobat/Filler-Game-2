package com.example.filler.logic.ai

import com.example.filler.constants.GameColor
import com.example.filler.logic.colors.Generator
import com.example.filler.logic.colors.RandomColorGenerator

class HardModeColorGenerator(private val settings: AIGeneratorSettings) :
    ColorGenerator(settings), Generator
{
    override fun generate(): GameColor = chooseColor(getColors(getColorsByGoodness()), 0)

    override fun chooseColor(colors: List<GameColor>, nUsefulChoices: Int): GameColor = colors.first()
}