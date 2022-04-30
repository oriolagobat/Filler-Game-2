package com.example.filler.logic.player

import com.example.filler.constants.GameColor

interface AreaExpander {
    fun updatePlayerArea(player: Player, color: GameColor)
}