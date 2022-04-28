package com.example.filler.gui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.databinding.ActivityResultsDrawBinding
import com.example.filler.databinding.ActivityResultsLoseBinding
import com.example.filler.databinding.ActivityResultsWinBinding

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val resultType = intent.getStringExtra("resultType")

        // Inflate the view corresponding to the outcome of the game
        val binding = when (resultType) {
            "win" -> ActivityResultsWinBinding.inflate(layoutInflater)
            "loose" -> ActivityResultsLoseBinding.inflate(layoutInflater)
            "draw" -> ActivityResultsDrawBinding.inflate(layoutInflater)
            else -> throw IllegalArgumentException("No more possible results")
        }
        setContentView(binding.root)

        val playerIntent = Intent(this, SongPlayer::class.java)
        when (resultType) {
            "win" -> playerIntent.putExtra("song", R.raw.win)
            "loose" -> playerIntent.putExtra("song", R.raw.lose)
            "draw" -> playerIntent.putExtra("song", R.raw.draw)
            else -> throw IllegalArgumentException("No more possible results")
        }
        stopService(playerIntent)
        startService(playerIntent)
    }
}