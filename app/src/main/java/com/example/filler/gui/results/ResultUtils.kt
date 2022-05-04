package com.example.filler.gui.results

import android.content.Intent
import android.net.Uri
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.filler.R
import com.example.filler.constants.gui.Music
import com.example.filler.constants.gui.Outcomes
import com.example.filler.constants.gui.Scores
import com.example.filler.databinding.ActivityResultsBinding
import com.example.filler.gui.game.data.Score
import com.example.filler.gui.shared.SongPlayer
import com.example.filler.gui.shared.getValidMailOrError

fun setUpResultListeners(
    context: Results,
    binding: ActivityResultsBinding
) {
    binding.emailInput.setOnClickListener(context)
    binding.sendEmailButton.setOnClickListener(context)
    binding.restartGameButton.setOnClickListener(context)
    binding.closeButton.setOnClickListener(context)
}

fun startSongPlayer(
    context: Results,
    startIntent: Intent
) {
    val playerIntent = Intent(context, SongPlayer::class.java)
    playerIntent.putExtra(Music.LOOP.name, false)  // Makes it not loop
    when (startIntent.getStringExtra(Outcomes.OUTCOME.name)) {
        Outcomes.WIN.toString() -> playerIntent.putExtra(Music.SONG.name, R.raw.win)
        Outcomes.LOSE.toString() -> playerIntent.putExtra(Music.SONG.name, R.raw.lose)
        Outcomes.DRAW.toString() -> playerIntent.putExtra(Music.SONG.name, R.raw.draw)
    }
    context.startService(playerIntent)
}

fun updateScoreText(
    context: Results,
    intent: Intent,
    binding: ActivityResultsBinding
) {
    val player1Score: Score = intent.getSerializableExtra(Scores.PLAYER1SCORE.name) as Score
    val player1Text = "Your score: ${player1Score.value}"
    binding.p1Score.text = player1Text

    val player2Score = intent.getSerializableExtra(Scores.PLAYER2SCORE.name) as Score
    val player2Text = "AI score: ${player2Score.value}"
    binding.aiScore.text = player2Text

    setCorrectTextColor(context, intent, binding)
}

private fun setCorrectTextColor(
    context: Results,
    intent: Intent,
    binding: ActivityResultsBinding
) {
    val (p1Color, p2Color) = when (intent.getStringExtra(Outcomes.OUTCOME.name)) {
        Outcomes.WIN.name -> Pair(R.color.green, R.color.red)
        Outcomes.LOSE.name -> Pair(R.color.yellow, R.color.yellow)
        Outcomes.DRAW.name -> Pair(R.color.red, R.color.green)
        else -> throw IllegalArgumentException("No more possible results")
    }
    binding.p1Score.setTextColor(ContextCompat.getColor(context, p1Color))
    binding.aiScore.setTextColor(ContextCompat.getColor(context, p2Color))
}

fun saveEmail(
    context: Results,
    emailInput: EditText
): String {
    return getValidMailOrError(context, emailInput)
}

fun checkAndSendMail(
    context: Results,
    email: String?,
    date: String,
    log: String
) {
    checkUnenteredEmail(context, email)
    sendMailIntent(context, email, date, log)
}

private fun checkUnenteredEmail(
    context: Results,
    email: String?,
): String {
    if (email == null || email.isEmpty()) {
        Toast.makeText(
            context,
            "No email introduced, default one will be used...",
            Toast.LENGTH_SHORT
        ).show()
        return context.getString(R.string.results_hint_email)
    }
    return email
}

private fun sendMailIntent(
    context: Results,
    email: String?,
    date: String,
    log: String
) {
    val uriMail = "mailto:$email"
    val uriSubject = "?subject=${Uri.encode("Filler: $date")}"
    val uriBody = "&body=${Uri.encode(log)}"

    val uri: Uri = Uri.parse("$uriMail$uriSubject$uriBody")

    val intent = Intent(Intent.ACTION_SENDTO, uri)
    context.startActivity(intent)
}