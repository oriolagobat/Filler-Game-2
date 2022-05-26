package com.example.filler.persistence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filler.FillerApplication
import com.example.filler.databinding.FragmentQueryBinding
import com.example.filler.logic.game.Game
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
        initUI()
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
            override fun onRowClicked(position: Int) {
                Toast.makeText(activity, "Clicked on row $position", Toast.LENGTH_SHORT).show()
            }

            override fun onRowLongClicked(view: View) {
//                showPopupMenu()
                Toast.makeText(activity, "Long clicked on row", Toast.LENGTH_SHORT).show()
            }

            override fun onRowDeleteClicked(summary: GameSummary) {
                gameSummaryViewModel.delete(summary)
            }
        }
    }

    private fun listenForSumariesUpdate() {
        gameSummaryViewModel.summaries.observe(viewLifecycleOwner) {
            adapter.summaries = gameSummaryViewModel.summaries.value!!
            adapter.notifyDataSetChanged()
        }
    }

//    private fun showPopupMenu() {
//        val popupMenu = PopupMenu(activity, binding.queryRecyclerView)
//        popupMenu.inflate(R.menu.popup_menu)
//        popupMenu.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.popup_menu_delete -> {
//                    Toast.makeText(activity, "Delete", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                else -> false
//            }
//        }
//        popupMenu.show()
//    }

    private fun setAdapter(adapter: GameSummaryListAdapter) {
        binding.gameSummaryRecyclerView.adapter = adapter
        binding.gameSummaryRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}