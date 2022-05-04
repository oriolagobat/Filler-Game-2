package com.example.filler.logic.score

import com.example.filler.constants.logic.GameColor

interface ScoreCalculator {
    fun updateAreas(pickedColor: GameColor)
}