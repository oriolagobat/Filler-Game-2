package com.example.filler.constants

import com.example.filler.logic.player.Player

data class GameData(
    var currentPlayer: Player,
    var enemyPlayer: Player,
    val round: Int,
    var state: GameState
)