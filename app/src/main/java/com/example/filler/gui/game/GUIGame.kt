package com.example.filler.gui.game

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameState
import com.example.filler.constants.gui.NewGame
import com.example.filler.constants.gui.Outcomes
import com.example.filler.constants.gui.PlayerType
import com.example.filler.constants.gui.Scores
import com.example.filler.databinding.ActivityGameBinding
import com.example.filler.gui.configuration.GameConfiguration
import com.example.filler.gui.configuration.data.Username
import com.example.filler.gui.hideNavBar
import com.example.filler.gui.results.Results
import com.example.filler.logic.game.Game
import com.example.filler.logic.game.GameFactoryImpl
import com.example.filler.logic.game.GameResponse
import com.example.filler.logic.game.GameSettings

class GUIGame : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the navbar
        hideNavBar(this)

        // Set this activity binding
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val guiSettings = intent.getSerializableExtra(NewGame.SETTINGS.name) as GameConfiguration
        // Parse difficulty to constant value
        val difficultyStr = guiSettings.difficultyString.value!!
        val difficulty = Difficulty.valueOf(difficultyStr.uppercase())
        val boardSize = getFirstIntFromString(guiSettings.boardSize.value!!)
        val colorNum = getFirstIntFromString(guiSettings.colorNumber.value!!)

        val settings = GameSettings(boardSize, colorNum, difficulty)

        // Initialize usernames and timer
        setUpTimersAndUsernames(guiSettings.username)

        // Initialize game
        game = GameFactoryImpl(settings).makeGame()

        // First Iteration
        firstGameIteration()
    }

    private fun getFirstIntFromString(str: String): Int {
        return str.first().digitToInt()
    }

    private fun setUpTimersAndUsernames(username: Username) {
        binding.usernameText.text = username.value
    }

    // Starts the game, making a first iteration to initialize everything
    private fun firstGameIteration() {
        val firstGameResponse = game.getGameResponse()
        val firstPlayer = PlayerType.HUMAN
        GameIteration(this, binding, game, firstGameResponse, firstPlayer).start()
    }

    fun nextIteration(gameResponse: GameResponse, nextPlayer: PlayerType) {
        if (!gameFinished(gameResponse)) {
            GameIteration(this, binding, game, gameResponse, nextPlayer).start()
        } else {
            startResultsActivity(gameResponse)
        }
    }

    private fun gameFinished(gameResponse: GameResponse): Boolean {
        return gameResponse.state == GameState.P1_WON ||
                gameResponse.state == GameState.P2_WON ||
                gameResponse.state == GameState.DRAW
    }

    private fun startResultsActivity(finalResponse: GameResponse) {
        val intent = Intent(this, Results::class.java)
        putOutComeData(intent, finalResponse)
        putPlayerScoreData(intent, finalResponse)
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
        intent.putExtra(Scores.PLAYER1SCORE.name, finalResponse.p1Score)
        intent.putExtra(Scores.PLAYER2SCORE.name, finalResponse.p2Score)
    }
}
