package com.example.filler.logic.game

interface GameFactory {
    fun makeGame(settings: GameSettings): Game
}