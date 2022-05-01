package com.example.filler.logic.game

import com.example.filler.constants.GameState
import com.example.filler.logic.player.Player

data class GameData(
    var currentPlayer: Player,
    var enemyPlayer: Player,
    val round: Int,
    var state: GameState
)