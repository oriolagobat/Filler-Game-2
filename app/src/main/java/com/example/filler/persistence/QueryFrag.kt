package com.example.filler.persistence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filler.FillerApplication
import com.example.filler.R
import com.example.filler.databinding.FragmentQueryBinding
import com.example.filler.persistence.database.GameSummary
import com.example.filler.persistence.database.GameSummaryViewModel
import com.example.filler.persistence.database.GameSummaryViewModelFactory

class QueryFrag : Fragment() {

    private var _binding: FragmentQueryBinding? = null
    private val binding get() = _binding!!
    private val gameSummaryViewModel: GameSummaryViewModel by viewModels {
        GameSummaryViewModelFactory((requireActivity().application as FillerApplication).repository)
    }
    private lateinit var adapter: GameSummaryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQueryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBarListeners()
        initUI()
    }

    private fun setToolBarListeners() {
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.ascendingPercentage -> {
                    gameSummaryViewModel.filterByAreaAsc()
                    true
                }
                R.id.descendingPercentage -> {
                    gameSummaryViewModel.filterByAreaDesc()
                    true
                }
                R.id.victories -> {
                    gameSummaryViewModel.filterByOutcome("Victory")
                    true
                }
                R.id.loses -> {
                    gameSummaryViewModel.filterByOutcome("Defeat")
                    true
                }
                R.id.draws -> {
                    gameSummaryViewModel.filterByOutcome("Draw")
                    true
                }
                else -> false
            }
        }
    }

    private fun initUI() {
        val summaries = getInitialData()
        val listener = setUpListener()
        adapter = GameSummaryListAdapter(summaries, listener)
        setAdapter(adapter)
        listenForSumariesUpdate()
    }

    private fun getInitialData(): List<GameSummary> {
        return gameSummaryViewModel.summaries.value!!
    }

    private fun setUpListener(): GameSummaryClickListener {
        return object : GameSummaryClickListener {
            override fun onRowClicked(summary: GameSummary) {
                itemClickCallback(summary)
            }

            override fun onRowLongClicked(view: View, summary: GameSummary) {
                showPopupMenu(view, summary)
            }

            override fun onRowDeleteClicked(summary: GameSummary) {
                gameSummaryViewModel.delete(summary)
            }
        }
    }

    private fun itemClickCallback(summary: GameSummary) {
        gameSummaryViewModel.updateCurrentSummary(summary)
        val parentActivity = requireActivity() as AccessBDActivity
        parentActivity.onItemClick(summary)
    }

    private fun listenForSumariesUpdate() {
        gameSummaryViewModel.summaries.observe(viewLifecycleOwner) { summaryList ->
            adapter.summaries = summaryList
            adapter.notifyDataSetChanged()
        }
    }

    private fun showPopupMenu(view: View, summary: GameSummary) {
        val popupMenu = PopupMenu(requireActivity(), view)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.popup_menu_filter_by_this_name -> {
                    gameSummaryViewModel.filterByAlias(summary.alias)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun setAdapter(adapter: GameSummaryListAdapter) {
        binding.gameSummaryRecyclerView.adapter = adapter
        binding.gameSummaryRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}