package com.example.filler.logic

import com.example.filler.constants.Colors

interface BoardInt {
    fun getP1Color(): Colors
    fun getP2Color(): Colors
    fun getColor(position: Position): Colors
}