package com.example.filler.persistence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.filler.FillerApplication
import com.example.filler.R
import com.example.filler.constants.logic.GameState
import com.example.filler.databinding.FragmentRegBinding
import com.example.filler.persistence.database.GameSummary
import com.example.filler.persistence.database.GameSummaryViewModel
import com.example.filler.persistence.database.GameSummaryViewModelFactory

class RegFrag : Fragment() {
    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!
    private val gameSummaryViewModel: GameSummaryViewModel by viewModels {
        GameSummaryViewModelFactory((requireActivity().application as FillerApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GameSummaryViewModel.currentSummary.observe(viewLifecycleOwner) { current ->
            setDetailView(current)
        }
    }

    private fun setDetailView(current: GameSummary?) {
        current?.apply {
            binding.persistenceAlias.text = alias
            binding.persistenceColors.text = numColors.toString()
            binding.persistenceEndDate.text = endTime
            binding.persistenceTotalTime.text = elapsedTime
            binding.persistenceConqueredCells.text = conqueredAreaPercent.toString().plus("%")
            binding.persistenceSize.text = gridSize.toString()
//            binding.persistenceOutcome.text = parseOutcome(outcome)
            setOutcome(outcome)
        }
    }

    private fun setOutcome(outcome: String) {
        when (outcome) {
            "Victory" -> setWinOutcome()
            "Defeat" -> setLoseOutcome()
            "Draw" -> setDrawOutcome()
        }
    }

    private fun setWinOutcome() {
        binding.persistenceOutcomeWin.text = getString(R.string.detail_win)
        binding.persistenceOutcomeLose.visibility = View.INVISIBLE
        binding.persistenceOutcomeDraw.visibility = View.INVISIBLE
    }

    private fun setLoseOutcome() {
        binding.persistenceOutcomeLose.text = getString(R.string.detail_lose)
        binding.persistenceOutcomeWin.visibility = View.INVISIBLE
        binding.persistenceOutcomeDraw.visibility = View.INVISIBLE
    }

    private fun setDrawOutcome() {
        binding.persistenceOutcomeDraw.text = getString(R.string.detail_draw)
        binding.persistenceOutcomeLose.visibility = View.INVISIBLE
        binding.persistenceOutcomeWin.visibility = View.INVISIBLE
    }

    private fun parseOutcome(outcome: String): String {
        return when (outcome) {
            GameState.P1_WON.name -> getString(R.string.detail_win)
            GameState.P2_WON.name -> getString(R.string.detail_lose)
            GameState.DRAW.name -> getString(R.string.detail_draw)
            else -> getString(R.string.detail_draw)
        }
    }
}