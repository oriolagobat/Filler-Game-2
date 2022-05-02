package com.example.filler.logic.colors

import com.example.filler.constants.GameColor

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

    override fun getAvailableColors(): List<GameColor> {
       return colors
           .filter { entry -> !entry.value }
           .flatMap { entry -> listOf(entry.key) }
    }

    override fun getSelectedColors(): List<GameColor> {
        return colors
            .filter { entry -> entry.value }
            .flatMap { entry -> listOf(entry.key) }
    }

    private fun countSelectedColors(): Int {
        return colors
            .filter { entry -> entry.value }
            .count()
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
        return colors.toList().toTypedArray()
    }

    override fun getTotalAmountOfColors(): Int {
        return getAvailableColors().count() + getSelectedColors().count()
    }
}