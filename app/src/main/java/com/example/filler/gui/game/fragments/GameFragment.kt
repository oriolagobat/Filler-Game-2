package com.example.filler.gui.game.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filler.databinding.GameFragmentBinding
import com.example.filler.gui.game.GUIGame
import com.example.filler.gui.game.startGameSong
import com.example.filler.gui.game.stopGameSong

class GameFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate corresponding layout
        val binding = GameFragmentBinding.inflate(inflater, container, false)
        startGameSong(activity as GUIGame)
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        stopGameSong(activity as GUIGame)
    }
}