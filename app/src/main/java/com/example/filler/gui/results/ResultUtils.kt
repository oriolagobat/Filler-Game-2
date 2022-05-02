package com.example.filler.gui.results

import android.content.Intent
import android.net.Uri
import android.widget.EditText
import android.widget.Toast
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
        "loose" -> playerIntent.putExtra("song", R.raw.lose)
        "draw" -> playerIntent.putExtra("song", R.raw.draw)
        else -> throw IllegalArgumentException("No more possible results")
    }
    context.startService(playerIntent)
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