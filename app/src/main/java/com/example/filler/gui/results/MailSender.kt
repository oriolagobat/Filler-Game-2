package com.example.filler.gui.results

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.filler.R
import com.example.filler.constants.gui.IMPLICIT_MAIL_BODY
import com.example.filler.constants.gui.IMPLICIT_MAIL_MAIL
import com.example.filler.constants.gui.IMPLICIT_MAIL_SUBJECT
import com.example.filler.constants.gui.IMPLICIT_MAIL_SUBJECT_APP_NAME
import com.example.filler.gui.results.data.Date
import com.example.filler.gui.results.data.Email
import com.example.filler.gui.results.data.Log

class MailSender(
    private val context: ResultsActivity,
    private val email: Email,
    private val date: Date,
    private val log: Log
) {
    fun send() {
        checkUnenteredEmail(context, email.value)
        updateLogSendMail()
        sendMailIntent(context, email.value, date.value, log.value)
    }

    private fun checkUnenteredEmail(
        context: ResultsActivity,
        email: String?,
    ): String {
        if (email == null || email.isEmpty()) {
            Toast.makeText(
                context,
                context.getString(R.string.results_mail_error),
                Toast.LENGTH_SHORT
            ).show()
            return context.getString(R.string.results_hint_email)
        }
        return email
    }

    private fun sendMailIntent(
        context: ResultsActivity,
        email: String?,
        date: String,
        log: String
    ) {
        val uriMail = IMPLICIT_MAIL_MAIL + email
        val uriSubject = IMPLICIT_MAIL_SUBJECT + Uri.encode(IMPLICIT_MAIL_SUBJECT_APP_NAME + date)
        val uriBody = IMPLICIT_MAIL_BODY + Uri.encode(log)

        val uri: Uri = Uri.parse("$uriMail$uriSubject$uriBody")

        val intent = Intent(Intent.ACTION_SENDTO, uri)
        context.startActivity(intent)
    }
}