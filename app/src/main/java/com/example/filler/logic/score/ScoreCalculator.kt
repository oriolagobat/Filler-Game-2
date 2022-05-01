package com.example.filler.logic.score

import com.example.filler.constants.GameColor

interface ScoreCalculator {
    fun updateAreas(pickedColor: GameColor)
}