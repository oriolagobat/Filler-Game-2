package com.example.filler.gui.game

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.constants.Difficulty
import com.example.filler.databinding.ActivityGameBinding
import com.example.filler.logic.GameSettings
import com.example.filler.logic.stub.GameStub

class Game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set this activity binding
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        val colorNum = intent.getIntExtra("colorNum", 0)
        val gridNum = intent.getIntExtra("gridNum", 0)
        val timeControl = intent.getBooleanExtra("timeControl", false)
        val difficultyString = intent.getStringExtra("difficulty")
        // Pase difficulty to contant value
        val difficulty = Difficulty.valueOf(difficultyString!!.uppercase())

        val message =
            "Username: $username\n" +
                    "Color: $colorNum\n" +
                    "Grid: $gridNum\n" +
                    "Time Control: $timeControl\n" +
                    "Difficulty: $difficulty"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        // This is how it will be
//        binding.gameGridView.numColumns = gridNum
        binding.gameGridView.numColumns = 3
        val settings = GameSettings(3, 3, difficulty)
        val gameStub: GameStub = GameStub(settings)
//        binding.gameGridView.adapter = GridAdapter(this, gameStub.initGame().boardColors)
    }
}
