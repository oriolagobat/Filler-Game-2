package com.example.filler.gui.results

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.constants.gui.Outcomes
import com.example.filler.databinding.ActivityResultsBinding
import com.example.filler.gui.configuration.NewGameConfiguration
import com.example.filler.gui.results.data.Date
import com.example.filler.gui.results.data.Email
import com.example.filler.gui.results.data.Log
import com.example.filler.gui.shared.hideNavBar
import com.example.filler.log.Logger
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Results : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityResultsBinding
    private val email = Email()
    private val date = Date()
    private val log = Log(Logger.getLog())

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the navbar
        hideNavBar(this)

        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logOutput.movementMethod = ScrollingMovementMethod()  // Makes the log scrollable
        val resultType = intent.getStringExtra(Outcomes.OUTCOME.name)
        binding.emailInput.requestFocus()  // Set focus on the email input

        // Update log with the outcome
        updateLogOutcome(intent)

        // Start the media player with the sound corresponding to the outcome of the game
        startSongPlayer(this, intent)

        // Set the corresponding layout image and text corresponding to the outcome of the game
        val (imageId, textId, imageDescId) = when (resultType) {
            Outcomes.WIN.name -> getWinInfo()
            Outcomes.LOSE.name -> getDrawInfo()
            Outcomes.DRAW.name -> getLoseInfo()
            else -> throw IllegalArgumentException("No more result types")
        }
        updateOutcomeTextImage(imageId, textId, imageDescId)
        updateScoreText(this, intent, binding)

        // Set the date and log of the game
        setCurrentDate()
        setLog()

        // Set listeners
        setUpResultListeners(this, binding)
    }

    private fun updateOutcomeTextImage(imageId: Int, textId: Int, imageDescId: Int) {
        binding.outcomeImage.setImageResource(imageId)
        binding.outcomeImage.contentDescription = getString(imageDescId)
        val text = getString(textId)
        binding.outcomeHeader.text = text
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCurrentDate() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        date.value = current.format(formatter)
        binding.dateTimeOutput.text = date.value
    }

    private fun setLog() {
        binding.logOutput.text = log.value
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.emailInput -> getEmail()
            R.id.sendEmailButton -> sendEmail()
            R.id.restartGameButton -> startNewGame()
            R.id.closeButton -> finish()
        }
    }

    private fun getEmail() {
        email.value = saveEmail(this, binding.emailInput)
        updateLogMail(email)
    }

    private fun sendEmail() {
        getEmail()
        MailSender(this, email, date, log).send()
    }

    private fun startNewGame() {
        val intent = Intent(this, NewGameConfiguration::class.java)
        startActivity(intent)
        finish()
    }
}