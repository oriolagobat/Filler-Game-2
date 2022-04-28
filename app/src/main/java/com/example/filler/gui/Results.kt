package com.example.filler.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filler.R
import com.example.filler.databinding.ActivityResultsDrawBinding
import com.example.filler.databinding.ActivityResultsLoseBinding
import com.example.filler.databinding.ActivityResultsWinBinding

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the view corresponding to the outcome of the game
        val binding = when (intent.getStringExtra("resultType")) {
            "win" -> ActivityResultsWinBinding.inflate(layoutInflater)
            "loose" -> ActivityResultsLoseBinding.inflate(layoutInflater)
            "draw" -> ActivityResultsDrawBinding.inflate(layoutInflater)
            else -> throw IllegalArgumentException("No more possible results")
        }
        setContentView(binding.root)
    }
}