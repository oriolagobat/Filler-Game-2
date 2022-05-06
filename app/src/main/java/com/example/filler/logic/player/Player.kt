package com.example.filler.logic.player

import com.example.filler.log.Logger

data class Player(val id: String, var score: Int, val area: PlayerArea) {
    fun updateScore() {
        score = area.totalArea.count()
        Logger.logDebug("$id score updated")
    }
}