package com.example.filler.gui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.databinding.ActivityResultsBinding

class Results : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val resultType = intent.getStringExtra("resultType")

        // Start the media player with the sound corresponding to the outcome of the game
        manageSongPlayer()

        // Set the corresponding layout image and text corresponding to the outcome of the game

        when (resultType) {
            "win" -> manageWinOutcome()
            "loose" -> manageLoseOutcome()
            "draw" -> manageDrawOutcome()
            else -> throw IllegalArgumentException("No more possible results")
        }
    }

    private fun manageSongPlayer() {
        val playerIntent = Intent(this, SongPlayer::class.java)
        when (intent.getStringExtra("resultType")) {
            "win" -> playerIntent.putExtra("song", R.raw.win)
            "loose" -> playerIntent.putExtra("song", R.raw.lose)
            "draw" -> playerIntent.putExtra("song", R.raw.draw)
            else -> throw IllegalArgumentException("No more possible results")
        }
        startService(playerIntent)
    }

    private fun manageWinOutcome() {
        binding.outcomeImage.setImageResource(R.drawable.result_win)
        binding.outcomeHeader.text = getString(R.string.results_win_header)
    }

    private fun manageLoseOutcome() {
        binding.outcomeImage.setImageResource(R.drawable.result_lose)
        binding.outcomeHeader.text = getString(R.string.results_lose_header)
    }

    private fun manageDrawOutcome() {
        binding.outcomeImage.setImageResource(R.drawable.result_draw)
        binding.outcomeHeader.text = getString(R.string.results_draw_header)
    }
}