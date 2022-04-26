package com.example.filler.logic.interfaces

import com.example.filler.logic.Position

interface PlayerArea {
    val fringe: MutableList<Position>
    val area: MutableList<Position>
    fun addPosition(position: Position)
    fun hasPosition(position: Position): Boolean
}