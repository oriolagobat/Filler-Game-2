package com.example.filler.timer

import android.widget.TextView
import com.example.filler.constants.logic.TIMER_PERIOD_MS
import java.util.*

class ChronoTimer(
    override var timerTextView: TextView
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

    override fun finish() = try {
        timer.cancel()
    } catch (e: IllegalStateException) {
        // Nothing, done because it can be stopped more than one time
    }

    override fun start() {
        // Not implemented since chronoTimer is not stopped between rounds
    }

    override fun cancel() {
        // Not implemented since chronoTimer is not stopped between rounds
    }
}