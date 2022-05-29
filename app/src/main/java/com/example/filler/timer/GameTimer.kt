package com.example.filler.timer

import android.widget.TextView

const val SECONDS_IN_MINUTE = 60
const val TIMER_PADDING_VALUE = 2
const val TIMER_PADDING_CHAR = '0'

interface GameTimer {
    var timerTextView: TextView
    fun init()
    fun start()
    fun cancel()
    fun intToFormattedTime(remainingTime: Int): String {
        val minutes = remainingTime / SECONDS_IN_MINUTE
        val seconds = remainingTime % SECONDS_IN_MINUTE
        return "${minutes.toString().padStart(TIMER_PADDING_VALUE, TIMER_PADDING_CHAR)}:" +
                seconds.toString().padStart(TIMER_PADDING_VALUE, TIMER_PADDING_CHAR)
    }
    fun finish()
}