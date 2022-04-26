package com.example.filler.logic

import com.example.filler.constants.GameColor

class ColorSelectorImpl(
    allColors: List<GameColor>,
    startingPlayerColor: GameColor,
    rivalColor: GameColor
) : ColorSelector {

    override val availableColors = hashSetOf<GameColor>()
    override val selectedColors = hashSetOf<GameColor>()
    private var leastRecentlySelected: GameColor

    init {
        availableColors.addAll(allColors)
        selectedColors.add(startingPlayerColor)
        selectedColors.add(rivalColor)
        leastRecentlySelected = startingPlayerColor
    }

    override fun select(selectedColor: GameColor) {
        selectedColors.remove(leastRecentlySelected)
        availableColors.add(leastRecentlySelected)
        leastRecentlySelected = selectedColor
        selectedColors.add(selectedColor)
    }

    override fun toArray() {
        TODO("Not yet implemented")
    }
}