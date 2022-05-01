package com.example.filler.logic.player

import com.example.filler.logic.board.Position

interface PlayerArea {
    val fringe: MutableSet<Position>
    val totalArea: MutableSet<Position>
    fun addPosition(position: Position)
    fun hasPosition(position: Position): Boolean
}