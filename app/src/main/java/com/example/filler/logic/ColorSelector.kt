package com.example.filler.logic

import com.example.filler.constants.GameColor

interface ColorSelector {
    val availableColors: HashSet<GameColor>
    val selectedColors: HashSet<GameColor>
    fun select(selectedColor : GameColor)
    fun toArray()
}