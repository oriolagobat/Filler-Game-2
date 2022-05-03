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
import com.example.filler.gui.hideNavBar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Results : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityResultsBinding
    private var email: String? = null
    private lateinit var date: String
    private lateinit var log: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the navbar
        hideNavBar(this)

        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logOutput.movementMethod = ScrollingMovementMethod()  // Makes the log scrollable
        val resultType = intent.getStringExtra(Outcomes.OUTCOME.name)

        // Start the media player with the sound corresponding to the outcome of the game
        startSongPlayer(this, intent)

        // Set the corresponding layout image and text corresponding to the outcome of the game
        val (imageId, text) = when (resultType) {
            Outcomes.WIN.name -> Pair(R.drawable.result_win, R.string.results_win_header)
            Outcomes.LOSE.name -> Pair(R.drawable.result_lose, R.string.results_lose_header)
            Outcomes.DRAW.name -> Pair(R.drawable.result_draw, R.string.results_draw_header)
            else -> throw IllegalArgumentException("No more result types")
        }
        updateOutcomeTextImage(imageId, text)

        updateScoreText(this, intent, binding)

        // Set the date and log of the game
        setCurrentDate()
        setLog()


        // Set listeners
        setUpResultListeners(this, binding)
    }

    private fun updateOutcomeTextImage(imageId: Int, textId: Int) {
        binding.outcomeImage.setImageResource(imageId)
        val text = getString(textId)
        binding.outcomeHeader.text = text
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCurrentDate() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        date = current.format(formatter)
        binding.dateTimeOutput.text = date
    }

    private fun setLog() {
        // TODO: Get the log
        // FIXME: Stub functionality
        log = getString(R.string.stub_log)
        binding.logOutput.text = log
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.emailInput -> getEmail()
            R.id.sendEmailButton -> sendEmail()
            R.id.restartGameButton -> restartGame()
            R.id.closeButton -> closeGame()
        }
    }

    private fun getEmail() {
        email = saveEmail(this, binding.emailInput)
    }

    private fun sendEmail() {
        getEmail()
        checkAndSendMail(this, email, date, log)
    }

    private fun restartGame() {
        val intent = Intent(this, NewGameConfiguration::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    // TODO: Finish the app
    private fun closeGame() {
        android.os.Process.killProcess(android.os.Process.myPid())
    }
}