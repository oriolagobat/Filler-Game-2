package com.example.filler.timer

import android.widget.TextView
import com.example.filler.constants.logic.Difficulty
import com.example.filler.constants.logic.TIMER_PERIOD_MS
import java.util.*

class ChronoTimer(
    override var timerTextView: TextView,
    val difficulty: Difficulty
) : GameTimer {
    private var timer = Timer()
    private var currentTime = 0

    override fun init() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() = updateTimer()
        }, 0, TIMER_PERIOD_MS)
    }

    private fun updateTimer() {
        timerTextView.text = intToFormattedTime((currentTime++))
    }

    override fun start() {}

    override fun cancel() {}

    override fun finish() = try {
        timer.cancel()
    } catch (e: IllegalStateException) {
    }
}