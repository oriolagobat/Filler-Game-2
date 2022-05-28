package com.example.filler.persistence

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.filler.FillerApplication
import com.example.filler.R
import com.example.filler.databinding.FragmentQueryBinding
import com.example.filler.databinding.FragmentRegBinding
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
        gameSummaryViewModel.currentSummary.observe(viewLifecycleOwner) { current ->
            binding.textview.text = current?.endTime ?: "No current game"
        }
    }
}