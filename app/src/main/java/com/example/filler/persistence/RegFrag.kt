package com.example.filler.persistence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filler.R
import com.example.filler.constants.gui.OUTCOME_DRAW
import com.example.filler.constants.gui.OUTCOME_LOSE
import com.example.filler.constants.gui.OUTCOME_WIN
import com.example.filler.constants.logic.GameState
import com.example.filler.databinding.FragmentRegBinding
import com.example.filler.persistence.database.GameSummary
import com.example.filler.persistence.database.GameSummaryViewModel

class RegFrag : Fragment() {
    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!

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
            setOutcome(outcome)
        }
    }

    private fun setOutcome(outcome: String) {
        when (outcome) {
            OUTCOME_WIN -> setWinOutcome()
            OUTCOME_LOSE -> setLoseOutcome()
            OUTCOME_DRAW -> setDrawOutcome()
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
}