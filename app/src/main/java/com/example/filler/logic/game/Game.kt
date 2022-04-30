package com.example.filler.logic.game

import com.example.filler.constants.GameColor
import com.example.filler.logic.player.Player

interface Game {
    fun initGame(): GameResponse
    fun pickPlayerColor(player: Player, color: GameColor): GameResponse
    fun pickP2ColorThroughAI(): GameResponse
    fun getGameInfo(): GameResponse
}