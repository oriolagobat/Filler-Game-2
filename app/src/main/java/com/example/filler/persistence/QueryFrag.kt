package com.example.filler.persistence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filler.databinding.FragmentQueryBinding

//TODO: Delete sample list
val summaries = mutableListOf(
    GameSummary("Player1", "Win"),
    GameSummary("Player2", "Lose"),
    GameSummary("Player3", "Draw"),
    GameSummary("Player4", "Win"),
    GameSummary("Player5", "Lose"),
    GameSummary("Player6", "Draw"),
    GameSummary("Player7", "Win"),
    GameSummary("Player8", "Lose"),
    GameSummary("Player9", "Draw"),
    GameSummary("Player10", "Win"),
)

class QueryFrag : Fragment() {

    private var _binding: FragmentQueryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View {
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
        adapter.submitList(summaries)
    }

    private fun setAdapter(adapter: GameSummaryListAdapter) {
        binding.gameSummaryRecyclerView.adapter = adapter
        binding.gameSummaryRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}