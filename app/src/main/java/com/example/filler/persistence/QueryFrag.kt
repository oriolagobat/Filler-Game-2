package com.example.filler.persistence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filler.FillerApplication
import com.example.filler.databinding.FragmentQueryBinding
import com.example.filler.persistence.database.GameSummaryViewModel
import com.example.filler.persistence.database.GameSummaryViewModelFactory

class QueryFrag : Fragment() {

    private var _binding: FragmentQueryBinding? = null
    private val binding get() = _binding!!
    private val gameSummaryViewModel: GameSummaryViewModel by viewModels {
        GameSummaryViewModelFactory((requireActivity().application as FillerApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQueryBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        val adapter = GameSummaryListAdapter()
        populateAdapter(adapter)
        setAdapter(adapter)
    }

    private fun populateAdapter(adapter: GameSummaryListAdapter) {
        gameSummaryViewModel.allGameSummaries.observe(requireActivity()) { words ->
            words.let { adapter.submitList(it) }
        }
    }

    private fun setAdapter(adapter: GameSummaryListAdapter) {
        binding.gameSummaryRecyclerView.adapter = adapter
        binding.gameSummaryRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}