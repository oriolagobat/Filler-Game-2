package com.example.filler.logic.interfaces

import com.example.filler.logic.Position

interface PlayerArea {
    val fringe: MutableSet<Position>
    val area: MutableSet<Position>
    fun addPosition(position: Position)
    fun hasPosition(position: Position): Boolean
}