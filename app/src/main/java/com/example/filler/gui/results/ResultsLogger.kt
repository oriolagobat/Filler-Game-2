package com.example.filler.gui.results

import android.content.Intent
import com.example.filler.constants.gui.*
import com.example.filler.gui.game.data.Score
import com.example.filler.gui.results.data.Email
import com.example.filler.log.Logger

fun updateLogOutcome(intent: Intent) {
    val player1Score: Score = intent.getSerializableExtra(Scores.PLAYER1SCORE.name) as Score
    val player2Score = intent.getSerializableExtra(Scores.PLAYER2SCORE.name) as Score

    val message = when (intent.getStringExtra(Outcomes.OUTCOME.name)) {
        Outcomes.WIN.name -> RESULT_OUTCOME_MESSAGE_INTRO_WIN
        Outcomes.LOSE.name -> RESULT_OUTCOME_MESSAGE_INTRO_LOSE
        Outcomes.DRAW.name -> RESULT_OUTCOME_MESSAGE_INTRO_DRAW
        else -> throw IllegalArgumentException("No more possible outcomes")
    } + player1Score.value + RESULT_OUTCOME_MESSAGE_FIN + player2Score.value

    Logger.logInfo(message)
}

fun updateLogMail(mail: Email) {
    Logger.logInfo(RESULT_NEW_MAIL + mail.value)
}

fun updateLogSendMail() {
    Logger.logInfo(RESULT_SEND_MAIL)
}