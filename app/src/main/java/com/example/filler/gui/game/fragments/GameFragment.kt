package com.example.filler.gui.game.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.filler.R
import com.example.filler.constants.gui.*
import com.example.filler.constants.logic.Difficulty
import com.example.filler.constants.logic.GameState
import com.example.filler.databinding.GameFragmentBinding
import com.example.filler.gui.game.*
import com.example.filler.gui.game.data.Score
import com.example.filler.gui.game.viewmodel.GUIGameViewModel
import com.example.filler.gui.results.ResultsActivity
import com.example.filler.gui.shared.getPreferences
import com.example.filler.logic.game.GameResponse
import com.example.filler.logic.game.GameSettings
import com.example.filler.persistence.database.GameSummary

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
        val chosenUsername =
            preferences.getString(getString(R.string.pref_alias_key), ALIAS_DEFAULT)
        if (validAlias(chosenUsername!!)) binding.usernameText.text = chosenUsername
        else {
            setDefaultAlias()
            binding.usernameText.text = ALIAS_DEFAULT
        }
    }

    private fun setDefaultAlias() {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .edit()
            .putString(getString(R.string.pref_alias_key), getString(R.string.pref_alias_default))
            .apply()
    }

    private fun validAlias(alias: String): Boolean {
        return alias.lines().size <= MAX_ALIAS_LINES && alias.length <= MAX_ALIAS_LENGTH
    }

    private fun setPfp() {
        binding.userPFP.setImageURI(
            Uri.parse(preferences.getString(
                getString(R.string.pref_profile_pic_key),
                PROFILE_PIC_DEFAULT.toString()
            ))
        )
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
        val boardString =
            preferences.getString(getString(R.string.pref_board_key), BOARD_SIZE_DEFAULT.toString())
        return boardString!!.toInt()
    }

    private fun getColorNumber(): Int {
        val selectorString = preferences.getString(
            getString(R.string.pref_colors_key),
            NUM_COLORS_DEFAULT.toString()
        )
        return selectorString!!.toInt()
    }

    private fun getDifficulty(): Difficulty {
        return when (preferences.getString(
            getString(R.string.pref_difficulty_key),
            DIFFICULTY_DEFAULT
        )) {
            DIFFICULTY_EASY -> Difficulty.EASY
            DIFFICULTY_MEDIUM -> Difficulty.MEDIUM
            DIFFICULTY_HARD -> Difficulty.HARD
            else -> Difficulty.MEDIUM
        }
    }

    private fun getTimeControl(): Boolean {
        return preferences.getBoolean(getString(R.string.pref_timer_key), TIME_CONTROL_DEFAULT)
    }

    fun startResultsActivity(finalResponse: GameResponse, summary: GameSummary) {
        val intent = Intent(requireContext(), ResultsActivity::class.java)
        putOutComeData(intent, finalResponse)
        putPlayerScoreData(intent, finalResponse)
        putSummaryData(intent, summary)
        stopGameSong(requireActivity() as GameActivity)
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

    private fun putSummaryData(intent: Intent, summary: GameSummary) {
        intent.putExtra(Summary.GAMESUMMARY.name, summary)
    }

    override fun onDestroy() {
        super.onDestroy()
        gameViewModel.mutableGameMediator.value!!.timer.finish()
    }
}
