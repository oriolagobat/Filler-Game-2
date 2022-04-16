package com.example.filler.logic

import com.example.filler.constants.Colors

class Cell(val color: Colors, var x: Int, var y: Int) {
    fun hasSameColorAs(color: Colors): Boolean {
        return this.color == color
    }
}
