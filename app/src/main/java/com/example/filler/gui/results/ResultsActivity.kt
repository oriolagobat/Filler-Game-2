package com.example.filler.gui.results

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.filler.FillerApplication
import com.example.filler.R
import com.example.filler.constants.gui.ALIAS_DEFAULT
import com.example.filler.constants.gui.Outcomes
import com.example.filler.constants.gui.RESULT_DATE_FORMAT
import com.example.filler.databinding.ActivityResultsBinding
import com.example.filler.gui.game.GameActivity
import com.example.filler.gui.preferences.PreferencesActivity
import com.example.filler.gui.results.data.Date
import com.example.filler.gui.results.data.Email
import com.example.filler.gui.results.data.Log
import com.example.filler.gui.results.viewmodel.ResultsViewModel
import com.example.filler.gui.shared.hideNavBar
import com.example.filler.gui.shared.sound
import com.example.filler.log.Logger
import com.example.filler.persistence.database.GameSummary
import com.example.filler.persistence.database.GameSummaryViewModel
import com.example.filler.persistence.database.GameSummaryViewModelFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ResultsActivity : AppCompatActivity(), View.OnClickListener {
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

        val resultType = intent.getStringExtra(Outcomes.OUTCOME.name)
        binding.emailInput.requestFocus()  // Set focus on the email input

        checkFirstCreation()

        updateLayout(resultType!!)

        setUpResultListeners(this, binding)
    }

    private fun checkFirstCreation() {
        val resultViewModel = ViewModelProvider(this)[ResultsViewModel::class.java]
        if (resultViewModel.firstCreation.value!!) {
            updateLogOutcome(intent)  // Update log
            persistGameStats(intent)  // Persist game
            if (sound(this)) startSongPlayer(this, intent)  // Start song player
            resultViewModel.firstCreation.value = false
        }
    }

    private fun persistGameStats(intent: Intent) {
        //TODO: Ask for the game summary and persist it to the database
//        val summary = GameSummary(
//            alias = getAlias(),
//            outcome = getOutcome(intent)
//        )
//        gameSummaryViewModel.insert(summary)
    }

    private fun getAlias(): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        return sharedPreferences.getString(
            getString(R.string.pref_alias_key), ALIAS_DEFAULT
        ) ?: ALIAS_DEFAULT
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
        val formatter = DateTimeFormatter.ofPattern(RESULT_DATE_FORMAT)
        date.value = current.format(formatter)
        binding.dateTimeOutput.text = date.value
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
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        return
    }

}