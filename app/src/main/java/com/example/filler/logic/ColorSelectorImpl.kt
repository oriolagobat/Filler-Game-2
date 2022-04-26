package com.example.filler.logic

import com.example.filler.constants.GameColor
import com.example.filler.logic.interfaces.ColorSelector

class ColorSelectorImpl(
    inputColors: List<GameColor>
) : ColorSelector {

    private var colors = mutableMapOf<GameColor, Boolean>()
    private var newestColor: GameColor? = null
    private var oldestColor: GameColor? = null

    init {
        inputColors.forEach {
            colors[it] = false
        }
    }


    override fun select(selectedColor: GameColor) {
        if (countSelectedColors() < 2)
            selectInitialColor(selectedColor)
        else
            update(selectedColor)
    }

    private fun countSelectedColors(): Int {
        var selectedColors = 0
        colors.forEach {
            if (it.value) {
                selectedColors++
            }
        }
        return selectedColors
    }

    private fun selectInitialColor(selectedColor: GameColor) {
        oldestColor = newestColor
        newestColor = selectedColor
        colors[selectedColor] = true
    }

    private fun update(selectedColor: GameColor) {
        colors[oldestColor!!] = false
        colors[selectedColor] = true
        oldestColor = newestColor
        newestColor = selectedColor
    }

    override fun toArray(): Array<Pair<GameColor, Boolean>> {
        var colorArray = arrayOf<Pair<GameColor, Boolean>>()
        colors.forEach {
            colorArray += (Pair(it.key, it.value))
        }
        return colorArray
    }
}