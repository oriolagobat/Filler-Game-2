package com.example.filler.gui.game

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.filler.databinding.ActivityGameBinding

class GUIGame : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        return
    }
}