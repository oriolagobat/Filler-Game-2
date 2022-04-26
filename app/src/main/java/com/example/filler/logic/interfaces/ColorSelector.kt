package com.example.filler.logic.interfaces

import com.example.filler.constants.GameColor

interface ColorSelector {
    fun select(selectedColor : GameColor)
    fun toArray(): Array<Pair<GameColor, Boolean>>
}