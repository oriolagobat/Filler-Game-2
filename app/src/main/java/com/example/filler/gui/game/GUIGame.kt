package com.example.filler.gui.game

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameState
import com.example.filler.constants.PlayerType
import com.example.filler.databinding.ActivityGameBinding
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

        val username = intent.getStringExtra("username")
        val colorNum = intent.getIntExtra("colorNum", 0)
        val gridNum = intent.getIntExtra("gridNum", 0)
        val timeControl = intent.getBooleanExtra("timeControl", false)
        val difficultyString = intent.getStringExtra("difficulty")
        // Parse difficulty to constant value
        val difficulty = Difficulty.valueOf(difficultyString!!.uppercase())

        val settings = GameSettings(gridNum, colorNum, difficulty)

        // Initialize usernames and timer
        setUpTimersAndUsernames(username!!)

        // Initialize game
        game = GameFactoryImpl(settings).makeGame()

        // First Iteration
        firstGameIteration()
    }

    private fun setUpTimersAndUsernames(username: String) {
        binding.usernameText.text = username
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
        val stringId = "resultType"
        when (finalResponse.state) {
            GameState.P1_WON -> intent.putExtra(stringId, "win")
            GameState.P2_WON -> intent.putExtra(stringId, "lose")
            GameState.DRAW -> intent.putExtra(stringId, "draw")
            else -> throw IllegalArgumentException("Invalid finish state")
        }
    }

    private fun putPlayerScoreData(intent: Intent, finalResponse: GameResponse) {
        intent.putExtra("player1Score", finalResponse.p1Score)
        intent.putExtra("player2Score", finalResponse.p2Score)
    }
}
