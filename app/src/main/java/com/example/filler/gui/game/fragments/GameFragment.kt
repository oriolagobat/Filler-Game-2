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
import com.example.filler.logic.game.GameResponse
import com.example.filler.logic.game.GameSettings

class GameFragment : Fragment() {
    private lateinit var binding: GameFragmentBinding
    private lateinit var guiGameViewModel: GUIGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GameFragmentBinding.inflate(inflater, container, false)
        // TODO: Set up username and pfp
        checkRecreationAndStart()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        guiGameViewModel = ViewModelProvider(this)[GUIGameViewModel::class.java]
    }

    private fun checkRecreationAndStart() {
        if (setUpGameViewModel(guiGameViewModel)) {
            startGameMediator()
            guiGameViewModel.setUpViewModel.value = true
        } else refreshBoardSelectorReference(guiGameViewModel, binding)
        guiGameViewModel.mutableGameMediator.value?.start()
    }

    // Starts the game, making a first iteration to initialize everything
    private fun startGameMediator() {
        // FIXME: Removed settings from here and gameMediator constructor and get them directly from shared preferences
        val settings = GameSettings(6, 4, Difficulty.MEDIUM, false)
        val gameMediator =
            GameMediator(this, settings, getBoard(binding), getSelector(binding), getTimer(binding))
        guiGameViewModel.mutableGameMediator.value = gameMediator
    }

    fun startResultsActivity(gameState: GameResponse) {
        // TODO: Start result class
//        val intent = Intent(this, Results::class.java)
//        putOutComeData(intent, finalResponse)
//        putPlayerScoreData(intent, finalResponse)
//        stopGameSong(this)
//        startActivity(intent)
        requireActivity().finish()
    }
}