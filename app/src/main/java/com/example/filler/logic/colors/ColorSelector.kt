package com.example.filler.logic.colors

import com.example.filler.constants.GameColor

interface ColorSelector {
    fun select(selectedColor : GameColor)
    fun getAvailableColors(): List<GameColor>
    fun getSelectedColors(): List<GameColor>
    fun toArray(): Array<Pair<GameColor, Boolean>>
    fun getTotalAmountOfColors(): Int
}