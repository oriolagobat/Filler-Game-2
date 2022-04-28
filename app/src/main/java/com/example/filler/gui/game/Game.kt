package com.example.filler.gui.game

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.constants.Difficulty
import com.example.filler.constants.GameColor
import com.example.filler.constants.GridType
import com.example.filler.databinding.ActivityGameBinding
import com.example.filler.logic.GameResponse
import com.example.filler.logic.GameSettings
import com.example.filler.logic.stub.GameStub9x9

class Game : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        // This is how it will be
        val settings = GameSettings(gridNum, colorNum, difficulty)

        // Initialize usernames and timer
        setUpTimersAndUsernames(username!!)

        GameIteration(this, binding, settings).start()
    }

    private fun setUpTimersAndUsernames(username: String) {
        binding.playerName.text = username
    }
}
