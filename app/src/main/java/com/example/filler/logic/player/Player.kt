package com.example.filler.logic.player

data class Player(var score: Int, val area: PlayerArea) {
    fun updateScore() {
        score = area.totalArea.count()
    }
}