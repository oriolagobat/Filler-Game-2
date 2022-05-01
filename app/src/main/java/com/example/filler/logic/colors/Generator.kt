package com.example.filler.logic.colors

import com.example.filler.constants.GameColor

interface Generator {
    fun generate(): GameColor
}