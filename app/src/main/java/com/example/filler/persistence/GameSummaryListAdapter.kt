package com.example.filler.persistence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filler.R
import com.example.filler.persistence.database.GameSummary
import java.lang.ref.WeakReference

class GameSummaryListAdapter(
    var summaries: List<GameSummary>,
    private val listener: GameSummaryClickListener
) : RecyclerView.Adapter<GameSummaryListAdapter.GameSummaryViewHolder>() {

    inner class GameSummaryViewHolder(
        itemView: View,
        _listener: GameSummaryClickListener
    ) : RecyclerView.ViewHolder(itemView) {
        private val deleteButton: View = itemView.findViewById(R.id.game_summary_delete)
        private val listener = WeakReference(_listener)

        fun bind(summary: GameSummary) {
            setContent(summary)
            setListeners()
        }

        private fun setContent(summary: GameSummary) {
           setAlias(summary)
           setOutcome(summary)
           setDate(summary)
        }

        private fun setAlias(summary: GameSummary) {
            itemView.findViewById<TextView>(R.id.game_summary_alias).text = summary.alias
        }

        private fun setOutcome(summary: GameSummary) {
            itemView.findViewById<TextView>(R.id.game_summary_score).text = summary.outcome
        }

        private fun setDate(summary: GameSummary) {
            itemView.findViewById<TextView>(R.id.game_summary_date).text = summary.endTime
        }

        private fun setListeners() {
            setDetailListener()
            setQueryListener()
            setDeleteListener()
        }

        private fun setDetailListener() {
            itemView.setOnClickListener {
                listener.get()?.onRowClicked(bindingAdapterPosition)
            }
        }

        private fun setQueryListener() {
            itemView.setOnLongClickListener {
                listener.get()?.onRowLongClicked(itemView)
                true
            }
        }

        private fun setDeleteListener() {
            deleteButton.setOnClickListener {
                listener.get()?.onRowDeleteClicked(summaries[bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameSummaryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.game_summary_item, parent, false)
        return GameSummaryViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: GameSummaryViewHolder, position: Int) {
        holder.bind(summaries[position])
    }

    override fun getItemCount(): Int {
        return summaries.size
    }
}