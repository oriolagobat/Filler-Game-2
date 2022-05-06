package com.example.filler.timer

import android.widget.TextView

interface GameTimer {
    var timerTextView: TextView
    fun start()
    fun cancel()
    fun intToFormattedTime(remainingTime: Int): String {
        val minutes = remainingTime / 60
        val seconds = remainingTime % 60
        return "${minutes.toString().padStart(2, '0')}:" +
                seconds.toString().padStart(2, '0')
    }

    fun finish()
}