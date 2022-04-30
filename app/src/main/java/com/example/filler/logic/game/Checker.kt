package com.example.filler.logic.game

import com.example.filler.constants.GameState

interface Checker {
    fun updateState(state: GameState)
}