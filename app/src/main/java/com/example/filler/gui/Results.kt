package com.example.filler.gui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.databinding.ActivityResultsBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Results : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logOutput.movementMethod = ScrollingMovementMethod()  // Makes the log scrollable
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

        setCurrentDate()
        setLog()
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

    // TODO: Should this be my job or the logic one?
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCurrentDate() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        val date = current.format(formatter)
        binding.dateTimeOutput.text = date
    }

    private fun setLog() {
        // TODO: Get the log
        // FIXME: Stub functionality
        val log = getString(R.string.stub_log)
        binding.logOutput.text = log
    }
}