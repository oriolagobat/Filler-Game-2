package com.example.filler.persistence

import android.view.View
import com.example.filler.persistence.database.GameSummary

interface GameSummaryClickListener {
    fun onRowClicked(summary: GameSummary)
    fun onRowLongClicked(view: View, summary: GameSummary)
    fun onRowDeleteClicked(summary: GameSummary)
}