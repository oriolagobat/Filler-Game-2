package com.example.filler.persistence

interface GameSummaryClickListener {
    fun onRowClicked(position: Int)
    fun onRowLongClicked(position: Int)
    fun onRowDeleteClicked(position: Int)
}