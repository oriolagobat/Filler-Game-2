package com.example.filler.gui.game

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import com.example.filler.R
import com.example.filler.databinding.ActivityGameBinding
import com.example.filler.gui.game.fragments.GameFragment

class GUIGame : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val gameFrag = binding.gameFragment
//        val gameFrag = supportFragmentManager
//            .findFragmentById(R.id.gameFragment) as GameFragment?
    }
}