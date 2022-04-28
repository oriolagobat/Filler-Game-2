package com.example.filler.gui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.R
import com.example.filler.databinding.ActivityResultsBinding

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityResultsBinding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val resultType = intent.getStringExtra("resultType")

        when (resultType) {
            "win" -> binding.textView.text = "win"
            "loose" -> binding.textView.text = "lose"
            "draw" -> binding.textView.text = "draw"
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