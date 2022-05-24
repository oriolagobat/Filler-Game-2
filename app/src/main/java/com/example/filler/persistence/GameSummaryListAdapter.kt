package com.example.filler.persistence

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filler.R
import com.example.filler.persistence.database.GameSummary

class GameSummaryListAdapter(
) : ListAdapter<GameSummary, GameSummaryListAdapter.GameSummaryViewHolder>(GameSummaryComparator()) {

    inner class GameSummaryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnCreateContextMenuListener
    {
        private val aliasTextView: TextView = itemView.findViewById(R.id.alias)
        private val outcomeTextView: TextView = itemView.findViewById(R.id.outcome)

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        fun bind(alias: String, outcome: String) {
            aliasTextView.text = alias
            outcomeTextView.text = outcome
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.setHeaderTitle("Customize entries")
            menu?.add(0, v!!.id, 0, "Delete")
            menu?.add(0, v!!.id, 0, "Filter by this username")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameSummaryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.game_summary_item, parent, false)
        return GameSummaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameSummaryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.alias, current.outcome)
    }

    class GameSummaryComparator : DiffUtil.ItemCallback<GameSummary>() {
        override fun areItemsTheSame(oldItem: GameSummary, newItem: GameSummary): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GameSummary, newItem: GameSummary): Boolean {
            return oldItem.alias == newItem.alias && oldItem.outcome == newItem.outcome
        }
    }
}