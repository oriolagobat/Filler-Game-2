package com.example.filler.logic.game

import com.example.filler.constants.GameColor

interface Generator {
    fun generate(): GameColor
}