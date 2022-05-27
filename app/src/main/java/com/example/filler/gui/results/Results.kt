package com.example.filler.gui.results

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.filler.FillerApplication
import com.example.filler.R
import com.example.filler.constants.gui.ALIAS_DEFAULT
import com.example.filler.constants.gui.ALIAS_KEY
import com.example.filler.constants.gui.Outcomes
import com.example.filler.databinding.ActivityResultsBinding
import com.example.filler.gui.game.GUIGame
import com.example.filler.gui.preferences.PreferencesActivity
import com.example.filler.gui.results.data.Date
import com.example.filler.gui.results.data.Email
import com.example.filler.gui.results.data.Log
import com.example.filler.gui.shared.hideNavBar
import com.example.filler.gui.shared.sound
import com.example.filler.log.Logger
import com.example.filler.persistence.database.GameSummary
import com.example.filler.persistence.database.GameSummaryViewModel
import com.example.filler.persistence.database.GameSummaryViewModelFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Results : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityResultsBinding
    private val email = Email()
    private val date = Date()
    private val log = Log(Logger.getLog())
    private val gameSummaryViewModel: GameSummaryViewModel by viewModels {
        GameSummaryViewModelFactory((application as FillerApplication).repository)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideNavBar(this)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logOutput.movementMethod = ScrollingMovementMethod()  // Makes the log scrollable
        val resultType = intent.getStringExtra(Outcomes.OUTCOME.name)
        binding.emailInput.requestFocus()  // Set focus on the email input

        // Update log with the outcome
        updateLogOutcome(intent)

        // Persist game to database
        persistGameStats(intent)

        // Start the media player with the sound corresponding to the outcome of the game
        if (sound(this)) startSongPlayer(this, intent)

        updateLayout(resultType!!)

        setUpResultListeners(this, binding)
    }

    private fun persistGameStats(intent: Intent) {
        val summary = GameSummary(
            alias = getAlias(),
            outcome = getOutcome(intent)
        )
        gameSummaryViewModel.insert(summary)
    }

    private fun getAlias(): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        return sharedPreferences.getString(ALIAS_KEY, ALIAS_DEFAULT) ?: ALIAS_DEFAULT
    }

    private fun getOutcome(intent: Intent): String {
        return intent.getStringExtra(Outcomes.OUTCOME.name) ?: ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateLayout(resultType: String) {
        // Set the corresponding layout image and text corresponding to the outcome of the game
        val (imageId, textId, imageDescId) = when (resultType) {
            Outcomes.WIN.name -> getWinInfo()
            Outcomes.LOSE.name -> getLoseInfo()
            Outcomes.DRAW.name -> getDrawInfo()
            else -> throw IllegalArgumentException("No more result types")
        }
        updateOutcomeTextImage(imageId, textId, imageDescId)
        updateScoreText(this, intent, binding)
        setCurrentDate()
        setLog()
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
            R.id.settingsButton -> editSettings()
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

    private fun editSettings() {
        val intent = Intent(this, PreferencesActivity::class.java)
        startActivity(intent)
    }

    private fun startNewGame() {
        Logger.clearLog()
        val intent = Intent(this, GUIGame::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        return
    }

}