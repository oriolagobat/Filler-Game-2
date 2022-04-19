package com.example.filler.logic

import com.example.filler.constants.GameColor

interface Generator {
    fun generate(): GameColor
}