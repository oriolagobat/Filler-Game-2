package com.example.filler.gui.game

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.filler.constants.gui.Intents
import com.example.filler.constants.gui.Outcomes
import com.example.filler.constants.gui.Scores
import com.example.filler.constants.logic.Difficulty
import com.example.filler.constants.logic.GameState
import com.example.filler.databinding.ActivityGameBinding
import com.example.filler.gui.configuration.GameConfiguration
import com.example.filler.gui.configuration.data.Username
import com.example.filler.gui.game.data.Score
import com.example.filler.gui.results.Results
import com.example.filler.gui.shared.hideNavBar
import com.example.filler.logic.game.GameResponse
import com.example.filler.logic.game.GameSettings

class GUIGame : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private lateinit var settings: GameSettings
    private lateinit var guiGameViewModel: GUIGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideNavBar(this)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        guiGameViewModel = ViewModelProvider(this)[GUIGameViewModel::class.java]
        startGameSong(this)

        val guiSettings = intent.getSerializableExtra(Intents.SETTINGS.name) as GameConfiguration
        // Parse values to its type
        val difficultyStr = guiSettings.difficultyString.value!!
        val difficulty = Difficulty.valueOf(difficultyStr.uppercase())
        val boardSize = getFirstIntFromString(guiSettings.boardSize.value!!)
        val colorNum = getFirstIntFromString(guiSettings.colorNumber.value!!)

        // Create game settings
        settings = GameSettings(boardSize, colorNum, difficulty)

        // Initialize usernames and timer
        setUpTimersAndUsernames(guiSettings.username)

        checkRecreationAndStart()
    }

    private fun checkRecreationAndStart() {
        if (setUpViewModel(guiGameViewModel)) {
            startGameMediator()
            guiGameViewModel.setUpViewModel.value = true
        } else refreshBoardSelectorReference(guiGameViewModel, binding)
        guiGameViewModel.mutableGameMediator.value?.start()
    }

    private fun setUpTimersAndUsernames(username: Username) {
        binding.usernameText.text = username.value
        // TODO: Set up timer
    }

    // Starts the game, making a first iteration to initialize everything
    private fun startGameMediator() {
        val gameMediator = GameMediator(this, settings, getBoard(binding), getSelector(binding))
        guiGameViewModel.mutableGameMediator.value = gameMediator
    }

    fun startResultsActivity(finalResponse: GameResponse) {
        val intent = Intent(this, Results::class.java)
        putOutComeData(intent, finalResponse)
        putPlayerScoreData(intent, finalResponse)
        stopGameSong(this)
        startActivity(intent)
    }

    private fun putOutComeData(intent: Intent, finalResponse: GameResponse) {
        when (finalResponse.state) {
            GameState.P1_WON -> intent.putExtra(Outcomes.OUTCOME.name, Outcomes.WIN.name)
            GameState.P2_WON -> intent.putExtra(Outcomes.OUTCOME.name, Outcomes.LOSE.name)
            GameState.DRAW -> intent.putExtra(Outcomes.OUTCOME.name, Outcomes.DRAW.name)
            else -> throw IllegalArgumentException("Invalid finish state")
        }
    }

    private fun putPlayerScoreData(intent: Intent, finalResponse: GameResponse) {
        intent.putExtra(Scores.PLAYER1SCORE.name, Score(finalResponse.p1Score))
        intent.putExtra(Scores.PLAYER2SCORE.name, Score(finalResponse.p2Score))
    }
}
