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
        Outcomes.WIN.name -> "You won! With a score of ${player1Score.value} points against ${player2Score.value} from the AI"
        Outcomes.LOSE.name -> "You lost... You got ${player1Score.value} points against ${player2Score.value} from the AI"
        Outcomes.DRAW.name -> "It's a draw! You both got ${player1Score.value} points"
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