package com.example.filler.logic.ai

import com.example.filler.constants.GameColor
import com.example.filler.logic.colors.Generator
import com.example.filler.logic.colors.RandomColorGenerator

class MediumModeColorGenerator(private val settings: AIGeneratorSettings): Generator {
    override fun generate(): GameColor {
        return RandomColorGenerator(settings.colors).generate()
    }
}