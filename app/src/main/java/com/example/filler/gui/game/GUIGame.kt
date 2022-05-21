package com.example.filler.gui.game

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.filler.databinding.ActivityGameBinding
import com.example.filler.gui.shared.hideNavBar

class GUIGame : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideNavBar(this)

        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        startGameSong(this)
    }

    override fun onPause() {
        super.onPause()
        stopGameSong(this)
    }

    override fun onBackPressed() {
        return
    }
}