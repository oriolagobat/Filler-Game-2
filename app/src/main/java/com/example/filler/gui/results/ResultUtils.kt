package com.example.filler.gui.results

import android.content.Intent
import android.net.Uri
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.filler.R
import com.example.filler.databinding.ActivityResultsBinding
import com.example.filler.gui.SongPlayer
import com.example.filler.gui.getText

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
    playerIntent.putExtra("loop", false)  // Makes it not loop
    when (startIntent.getStringExtra("resultType")) {
        "win" -> playerIntent.putExtra("song", R.raw.win)
        "lose" -> playerIntent.putExtra("song", R.raw.lose)
        "draw" -> playerIntent.putExtra("song", R.raw.draw)
        else -> throw IllegalArgumentException("No more possible results")
    }
    context.startService(playerIntent)
}

fun updateScoreText(
    context: Results,
    intent: Intent,
    binding: ActivityResultsBinding
) {
    val player1Score = intent.getIntExtra("player1Score", 0)
    val player1Text = "Your score: $player1Score"
    binding.p1Score.text = player1Text

    val player2Score = intent.getIntExtra("player2Score", 0)
    val player2Text = "AI score: $player2Score"
    binding.aiScore.text = player2Text

    setCorrectTextColor(context, intent, binding)
}

private fun setCorrectTextColor(
    context: Results,
    intent: Intent,
    binding: ActivityResultsBinding
) {
    val (p1Color, p2Color) = when (intent.getStringExtra("resultType")) {
        "win" -> Pair(R.color.green, R.color.red)
        "lose" -> Pair(R.color.yellow, R.color.yellow)
        "draw" -> Pair(R.color.red, R.color.green)
        else -> throw IllegalArgumentException("No more possible results")
    }
    binding.p1Score.setTextColor(ContextCompat.getColor(context, p1Color))
    binding.aiScore.setTextColor(ContextCompat.getColor(context, p2Color))
}

fun saveEmail(
    context: Results,
    emailInput: EditText
): String {
    return getText(context, emailInput)
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