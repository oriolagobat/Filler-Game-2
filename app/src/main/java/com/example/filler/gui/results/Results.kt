package com.example.filler.gui.results

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.databinding.ActivityResultsBinding
import com.example.filler.gui.configuration.NewGameConfiguration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Results : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityResultsBinding

    // Start necessary variables
    private lateinit var email: String
    private lateinit var date: String
    private lateinit var log: String
    private lateinit var emailInput: NewEmailInput

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logOutput.movementMethod = ScrollingMovementMethod()  // Makes the log scrollable
        val resultType = intent.getStringExtra("resultType")

        // Start the media player with the sound corresponding to the outcome of the game
        manageSongPlayer()

        // Set the corresponding layout image and text corresponding to the outcome of the game
        when (resultType) {
            "win" -> manageWinOutcome()
            "loose" -> manageLoseOutcome()
            "draw" -> manageDrawOutcome()
            else -> throw IllegalArgumentException("No more possible results")
        }

        // Set the date and log of the game
        setCurrentDate()
        setLog()

        // Create a new email input instance
        emailInput = NewEmailInput(this, binding.emailInput)

        // Set listeners
        binding.emailInput.setOnClickListener(this)
        binding.sendEmailButton.setOnClickListener(this)
        binding.restartGameButton.setOnClickListener(this)
        binding.closeButton.setOnClickListener(this)
    }

    private fun manageSongPlayer() {
        val playerIntent = Intent(this, SongPlayer::class.java)
        when (intent.getStringExtra("resultType")) {
            "win" -> playerIntent.putExtra("song", R.raw.win)
            "loose" -> playerIntent.putExtra("song", R.raw.lose)
            "draw" -> playerIntent.putExtra("song", R.raw.draw)
            else -> throw IllegalArgumentException("No more possible results")
        }
        startService(playerIntent)
    }

    private fun manageWinOutcome() {
        binding.outcomeImage.setImageResource(R.drawable.result_win)
        binding.outcomeHeader.text = getString(R.string.results_win_header)
    }

    private fun manageLoseOutcome() {
        binding.outcomeImage.setImageResource(R.drawable.result_lose)
        binding.outcomeHeader.text = getString(R.string.results_lose_header)
    }

    private fun manageDrawOutcome() {
        binding.outcomeImage.setImageResource(R.drawable.result_draw)
        binding.outcomeHeader.text = getString(R.string.results_draw_header)
    }

    // TODO: Should this be my job or the logic one?
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
            R.id.emailInput -> saveEmail()
            R.id.sendEmailButton -> sendEmail()
            R.id.restartGameButton -> restartGame()
            R.id.closeButton -> finish()
        }
    }

    private fun saveEmail() {
        email = emailInput.get()
    }

    private fun sendEmail() {
        email = emailInput.get()
        checkUnenteredEmail()
        sendMailIntent()
    }

    private fun sendMailIntent() {
        val uriMail = "mailto:$email"
        val uriSubject = "?subject=${Uri.encode("Filler: $date")}"
        val uriBody = "&body=${Uri.encode(log)}"

        val uri: Uri = Uri.parse("$uriMail$uriSubject$uriBody")

        val intent = Intent(Intent.ACTION_SENDTO, uri)
        startActivity(intent)
    }

    private fun checkUnenteredEmail() {
        if (!::email.isInitialized || email.isEmpty()) {
            Toast.makeText(
                this,
                "No email introduced, default one will be used...",
                Toast.LENGTH_SHORT
            ).show()
            email = getString(R.string.results_hint_email)
        }
    }

    // TODO: Check stack
    private fun restartGame() {
        val intent = Intent(this, NewGameConfiguration::class.java)
        startActivity(intent)
    }
}