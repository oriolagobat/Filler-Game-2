package com.example.filler.gui.game.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.filler.constants.gui.*
import com.example.filler.constants.logic.Difficulty
import com.example.filler.constants.logic.GameState
import com.example.filler.databinding.GameFragmentBinding
import com.example.filler.gui.game.*
import com.example.filler.gui.game.data.Score
import com.example.filler.gui.game.viewmodel.GUIGameViewModel
import com.example.filler.gui.results.Results
import com.example.filler.gui.shared.getPreferences
import com.example.filler.logic.game.GameResponse
import com.example.filler.logic.game.GameSettings

class GameFragment : Fragment() {
    private lateinit var gameViewModel: GUIGameViewModel
    private lateinit var binding: GameFragmentBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GameFragmentBinding.inflate(inflater, container, false)
        gameViewModel = ViewModelProvider(this)[GUIGameViewModel::class.java]
        preferences = getPreferences(requireContext())
        setUsername()
        setPfp()
        checkRecreationAndStart()
        return binding.root
    }

    private fun setUsername() {
        binding.usernameText.text = preferences.getString(ALIAS_KEY, ALIAS_DEFAULT)
    }

    private fun setPfp() {
        // TODO: Set image
    }

    private fun checkRecreationAndStart() {
        if (setUpGameViewModel(gameViewModel)) {
            startGameMediator()
            gameViewModel.setUpViewModel.value = true
        } else refreshBoardSelectorReference(gameViewModel, binding, this)
        gameViewModel.mutableGameMediator.value?.start()
    }

    private fun startGameMediator() {
        val settings = getInitialSettings()
        val gameMediator =
            GameMediator(this, settings, getBoard(binding), getSelector(binding), getTimer(binding))
        gameViewModel.mutableGameMediator.value = gameMediator
    }

    private fun getInitialSettings(): GameSettings {
        val boardSize = getBoardSize()
        val colorNumber = getColorNumber()
        val difficulty = getDifficulty()
        val timeControl = getTimeControl()
        return GameSettings(boardSize, colorNumber, difficulty, timeControl)
    }

    private fun getBoardSize(): Int {
        val boardString = preferences.getString(BOARD_SIZE_KEY, BOARD_SIZE_DEFAULT.toString())
        return boardString!!.toInt()
    }

    private fun getColorNumber(): Int {
        val selectorString = preferences.getString(NUM_COLORS_KEY, NUM_COLORS_DEFAULT.toString())
        return selectorString!!.toInt()
    }

    private fun getDifficulty(): Difficulty {
        return when (preferences.getString(DIFFICULTY_KEY, DIFFICULTY_DEFAULT)) {
            "easy" -> Difficulty.EASY
            "medium" -> Difficulty.MEDIUM
            "hard" -> Difficulty.HARD
            else -> Difficulty.MEDIUM
        }
    }

    private fun getTimeControl(): Boolean {
        return preferences.getBoolean(TIME_CONTROL_KEY, TIME_CONTROL_DEFAULT)
    }

    fun startResultsActivity(finalResponse: GameResponse) {
        val intent = Intent(requireContext(), Results::class.java)
        putOutComeData(intent, finalResponse)
        putPlayerScoreData(intent, finalResponse)
        stopGameSong(requireActivity() as GUIGame)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun putOutComeData(intent: Intent, finalResponse: GameResponse) {
        when (finalResponse.state) {
            GameState.P1_WON -> intent.putExtra(Outcomes.OUTCOME.name, Outcomes.WIN.name)
            GameState.P2_WON -> intent.putExtra(Outcomes.OUTCOME.name, Outcomes.LOSE.name)
            GameState.DRAW -> intent.putExtra(Outcomes.OUTCOME.name, Outcomes.DRAW.name)
            else -> throw IllegalArgumentException("Invalid finish state")
        }
    }

    private fun putPlayerScoreData(intent: Intent, finalResponse: GameResponse) {
        intent.putExtra(Scores.PLAYER1SCORE.name, Score(finalResponse.p1Score))
        intent.putExtra(Scores.PLAYER2SCORE.name, Score(finalResponse.p2Score))
    }

    override fun onDestroy() {
        super.onDestroy()
        gameViewModel.mutableGameMediator.value!!.timer.finish()
    }
}