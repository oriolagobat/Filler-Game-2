package com.example.filler.gui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.constants.Difficulty

class Game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val username = intent.getStringExtra("username")
        val colorNum = intent.getStringExtra("colorNum")
        val gridNum = intent.getStringExtra("gridNum")
        val timeControl = intent.getBooleanExtra("timeControl", false)
        val difficultyString = intent.getStringExtra("difficulty")
        val difficulty = Difficulty.valueOf(difficultyString!!.uppercase())

        val message =
            "Username: $username\n" +
                    "Color: $colorNum\n" +
                    "Grid: $gridNum\n" +
                    "Time Control: $timeControl\n" +
                    "Difficulty: $difficulty"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}