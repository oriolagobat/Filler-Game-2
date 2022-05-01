package com.example.filler.logic.newgame

import com.example.filler.logic.newgame.Game
import com.example.filler.logic.game.GameSettings

interface GameFactory {
    fun makeGame(settings: GameSettings): Game
}