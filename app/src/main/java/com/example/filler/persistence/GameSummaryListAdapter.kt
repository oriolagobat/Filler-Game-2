package com.example.filler.persistence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filler.R
import com.example.filler.persistence.database.GameSummary
import java.lang.ref.WeakReference

class GameSummaryListAdapter(
    private val listener: GameSummaryClickListener
) : ListAdapter<GameSummary, GameSummaryListAdapter.GameSummaryViewHolder>(GameSummaryComparator()) {

    inner class GameSummaryViewHolder(
        itemView: View,
        _listener: GameSummaryClickListener
    ) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener, View.OnLongClickListener{
        private val aliasTextView: TextView = itemView.findViewById(R.id.game_summary_alias)
        private val outcomeTextView: TextView = itemView.findViewById(R.id.game_summary_score)
        private val dateTextView: TextView = itemView.findViewById(R.id.game_summary_date)
        private val deleteButton: View = itemView.findViewById(R.id.game_summary_delete)
        private val listener = WeakReference(_listener)

        fun bind(alias: String, outcome: String) {
            aliasTextView.text = alias
            outcomeTextView.text = outcome
            setDeleteListener()
        }

        private fun setDeleteListener() {
            deleteButton.setOnClickListener {
                listener.get()?.onRowDeleteClicked(bindingAdapterPosition)
            }
        }

        override fun onClick(v: View?) {
            listener.get()?.onRowClicked(bindingAdapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            listener.get()?.onRowLongClicked(bindingAdapterPosition)
            return true
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameSummaryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.game_summary_item, parent, false)
        return GameSummaryViewHolder(view, listener)
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