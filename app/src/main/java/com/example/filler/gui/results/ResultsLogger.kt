package com.example.filler.gui.results

import android.content.Intent
import com.example.filler.constants.gui.Outcomes
import com.example.filler.constants.gui.Scores
import com.example.filler.gui.game.data.Score
import com.example.filler.gui.results.data.Email
import com.example.filler.log.Logger

fun updateLogOutcome(intent: Intent) {
    val player1Score: Score = intent.getSerializableExtra(Scores.PLAYER1SCORE.name) as Score
    val player2Score = intent.getSerializableExtra(Scores.PLAYER2SCORE.name) as Score

    val message = when (intent.getStringExtra(Outcomes.OUTCOME.name)) {
        Outcomes.WIN.name -> "P1 won. P1: ${player1Score.value} - P2: ${player2Score.value}"
        Outcomes.LOSE.name -> "P2 won. P1: ${player1Score.value} - P2: ${player2Score.value}"
        Outcomes.DRAW.name -> "Draw. P1: ${player1Score.value} - P2: ${player2Score.value}"
        else -> throw IllegalArgumentException("No more possible outcomes")
    }

    Logger.logInfo(message)
}

fun updateLogMail(mail: Email) {
    Logger.logInfo("New email saved: ${mail.value}")
}

fun updateLogSendMail() {
    Logger.logInfo("Sending log by mail...")
}