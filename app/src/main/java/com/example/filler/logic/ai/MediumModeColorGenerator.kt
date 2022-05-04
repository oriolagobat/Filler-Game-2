package com.example.filler.logic.ai

import com.example.filler.constants.logic.GameColor
import com.example.filler.logic.colors.Generator

class MediumModeColorGenerator(private val settings: AIGeneratorSettings) :
    ColorGenerator(settings), Generator {
    override fun generate(): GameColor {
        val colorsByGoodness = getColorsByGoodness()
        val nUsefulChoices = getUsefulChoices(colorsByGoodness)
        return chooseColor(getColors(colorsByGoodness), nUsefulChoices)
    }

    override fun chooseColor(colors: List<GameColor>, nUsefulChoices: Int): GameColor {
       return when (nUsefulChoices) {
           0 -> colors.random()
           1 -> colors.take(1).first()
           else -> colors.take(2).random()
       }
    }
}