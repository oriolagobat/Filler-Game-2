package com.example.filler.logic.ai

import com.example.filler.constants.logic.Difficulty
import com.example.filler.logic.colors.Generator

interface AIColorGeneratorFactory {
    fun makeGenerator(difficulty: Difficulty, settings: AIGeneratorSettings): Generator
}