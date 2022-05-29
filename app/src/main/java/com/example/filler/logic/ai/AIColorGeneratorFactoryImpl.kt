package com.example.filler.logic.ai

import com.example.filler.constants.logic.Difficulty
import com.example.filler.logic.colors.Generator

class AIColorGeneratorFactoryImpl : AIColorGeneratorFactory {
    override fun makeGenerator(difficulty: Difficulty, settings: AIGeneratorSettings): Generator {
        return when (difficulty) {
            Difficulty.EASY -> EasyModeColorGenerator(settings)
            Difficulty.MEDIUM -> MediumModeColorGenerator(settings)
            Difficulty.HARD -> HardModeColorGenerator(settings)
        }
    }
}