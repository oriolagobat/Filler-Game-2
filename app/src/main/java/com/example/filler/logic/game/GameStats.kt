package com.example.filler.logic.game

import com.example.filler.persistence.database.GameSummary
import java.text.SimpleDateFormat
import java.util.*

class GameStats {
    private lateinit var _startTime: Date
    private lateinit var _endTime: Date
    private var elapsedTime = ""
    private var startTime = ""
    private var endTime = ""
    private var outcome = ""
    var difficulty = ""
    private var conqueredAreaPercent = 0
    var p2Score: Int = 0
    var p1Score: Int = 0
    var numColors = 0
    var gridSize = 0

    fun setEndDate() {
        _endTime = getCurrentDateTime()
        endTime = _endTime.toString("dd/MM/yyyy HH:mm")
        setElapsedTime()
    }

    fun setStartDate() {
        _startTime = getCurrentDateTime()
        startTime = _startTime.toString("dd/MM/yyyy HH:mm:ss")
    }

    private fun setElapsedTime() {
        val elapsedTimeInSeconds = (_endTime.time - _startTime.time) / 1000
        val minutes = elapsedTimeInSeconds / 60
        val seconds = elapsedTimeInSeconds % 60
        this.elapsedTime = "$minutes:$seconds"
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun setP1ConqueredAreaPercent(area: Int, numCells: Int) {
        this.conqueredAreaPercent = area * 100 / numCells
    }

    fun makeSummary(): GameSummary {
        return GameSummary(
            endTime = endTime,
            elapsedTime = elapsedTime,
            alias = "",
            outcome = outcome,
            gridSize = gridSize,
            conqueredAreaPercent = conqueredAreaPercent,
            numColors = numColors
        )
    }

    fun setOutcome(outcome: String) {
        when (outcome) {
            "P1_WON" -> this.outcome = "Victory"
            "P2_WON" -> this.outcome = "Defeat"
            "DRAW" -> this.outcome = "Draw"
        }
    }
}
