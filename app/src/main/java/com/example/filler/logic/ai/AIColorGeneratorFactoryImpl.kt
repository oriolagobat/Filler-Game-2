package com.example.filler.logic.ai

import com.example.filler.constants.Difficulty
import com.example.filler.logic.interfaces.Board
import com.example.filler.logic.interfaces.Generator
import com.example.filler.logic.interfaces.PlayerArea

class AIColorGeneratorFactoryImpl: AIColorGeneratorFactory {
    override fun makeGenerator(difficulty: Difficulty, settings: AIGeneratorSettings): Generator {
        return when (difficulty) {
            Difficulty.EASY -> EasyModeColorGenerator(settings)
            Difficulty.MEDIUM -> MediumModeColorGenerator(settings)
            Difficulty.HARD -> MediumModeColorGenerator(settings)
        }
    }
}