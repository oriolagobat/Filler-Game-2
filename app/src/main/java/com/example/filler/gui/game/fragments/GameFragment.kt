package com.example.filler.gui.game.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.filler.constants.logic.Difficulty
import com.example.filler.databinding.GameFragmentBinding
import com.example.filler.gui.game.*
import com.example.filler.gui.game.viewmodel.GUIGameViewModel
import com.example.filler.logic.game.GameSettings

class GameFragment : Fragment() {
    private lateinit var gameViewModel: GUIGameViewModel
    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GameFragmentBinding.inflate(inflater, container, false)
        gameViewModel = ViewModelProvider(this)[GUIGameViewModel::class.java]
        checkRecreationAndStart()
        return binding.root
    }

    private fun checkRecreationAndStart() {
        if (setUpGameViewModel(gameViewModel)) {
            startGameMediator()
            gameViewModel.setUpViewModel.value = true
        } else refreshBoardSelectorReference(gameViewModel, binding, this)
        gameViewModel.mutableGameMediator.value?.start()
    }

    private fun startGameMediator() {
        val settings = GameSettings(6, 4, Difficulty.MEDIUM, false)
        val gameMediator =
            GameMediator(this, settings, getBoard(binding), getSelector(binding), getTimer(binding))
        gameViewModel.mutableGameMediator.value = gameMediator
    }

    override fun onDestroy() {
        super.onDestroy()
        gameViewModel.mutableGameMediator.value!!.timer.finish()
    }
}