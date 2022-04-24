package com.example.filler.logic

interface PlayerArea {
    val fringe: MutableList<Position>
    val area: MutableList<Position>
    fun addPosition(position: Position)
    fun hasPosition(position: Position): Boolean
}