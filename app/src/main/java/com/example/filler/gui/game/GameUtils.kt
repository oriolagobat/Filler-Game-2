package com.example.filler.gui.game

import com.example.filler.R
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

fun getColorFromGameColor(gameColor: GameColor): Int {
    return when (gameColor) {
        GameColor.PINK -> R.color.game_pink
        GameColor.ORANGE -> R.color.game_orange
        GameColor.YELLOW -> R.color.game_yellow
        GameColor.GREEN -> R.color.game_green
        GameColor.BLUE -> R.color.game_blue
        GameColor.PURPLE -> R.color.game_purple
        GameColor.CYAN -> R.color.game_cyan
        GameColor.BLACK -> R.color.black
        else -> throw UnsupportedOperationException("No more colors...")
    }
}