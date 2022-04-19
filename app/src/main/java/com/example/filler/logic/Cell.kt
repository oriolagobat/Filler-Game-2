package com.example.filler.logic

import com.example.filler.constants.Colors

class Cell(val color: Colors, val position: Position) {
    fun hasSameColorAs(cell: Cell): Boolean {
        return this.color == cell.color
    }
}
