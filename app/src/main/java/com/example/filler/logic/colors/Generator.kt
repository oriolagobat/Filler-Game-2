package com.example.filler.logic.colors

import com.example.filler.constants.logic.GameColor

interface Generator {
    fun generate(): GameColor
}