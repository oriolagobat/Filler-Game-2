package com.example.filler.gui.game

import com.example.filler.constants.GameColor


fun getArrayColors(array: Array<Pair<GameColor, Boolean>>): Array<GameColor> {
    return array.map { it.first }.toTypedArray()
}

fun colorUnClickable(
    array: Array<Pair<GameColor, Boolean>>,
    chosenColor: GameColor
): Boolean {
    for (pair in array) {
        if (pair.first == chosenColor && pair.second) {
            return true
        }
    }
    return false
}