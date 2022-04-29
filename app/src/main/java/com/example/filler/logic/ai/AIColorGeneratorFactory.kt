package com.example.filler.logic.ai

import com.example.filler.constants.Difficulty
import com.example.filler.logic.interfaces.Board
import com.example.filler.logic.interfaces.Generator
import com.example.filler.logic.interfaces.PlayerArea

interface AIColorGeneratorFactory {
    fun makeGenerator(difficulty: Difficulty, settings: AIGeneratorSettings): Generator
}