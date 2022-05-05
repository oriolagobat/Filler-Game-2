package com.example.filler.gui.results

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.filler.R
import com.example.filler.gui.results.data.Date
import com.example.filler.gui.results.data.Email
import com.example.filler.gui.results.data.Log

class MailSender(
    private val context: Results,
    private val email: Email,
    private val date: Date,
    private val log: Log
) {
    fun send() {
        checkUnenteredEmail(context, email.value)
        sendMailIntent(context, email.value, date.value, log.value)
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
}