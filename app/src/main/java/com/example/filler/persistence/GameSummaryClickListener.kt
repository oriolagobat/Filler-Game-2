package com.example.filler.persistence

import android.view.View
import com.example.filler.persistence.database.GameSummary

interface GameSummaryClickListener {
    fun onRowClicked(position: Int)
    fun onRowLongClicked(view: View)
    fun onRowDeleteClicked(summary: GameSummary)
}