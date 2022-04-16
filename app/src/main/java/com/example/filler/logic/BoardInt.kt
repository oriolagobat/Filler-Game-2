package com.example.filler.logic

import com.example.filler.constants.Colors

interface BoardInt {
    fun getRandomColor(): Colors
    fun getP1Color(): Colors
    fun getP2Color(): Colors
    fun getColor(x: Int, y: Int): Colors
}