package com.example.filler.logic

import android.provider.CalendarContract
import com.example.filler.constants.Colors

interface Filler {
    fun fill(): Array<Array<Cell>>
    fun getRandomColor(): Colors
}