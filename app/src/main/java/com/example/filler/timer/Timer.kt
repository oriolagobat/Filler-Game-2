package com.example.filler.timer

import android.os.CountDownTimer
import android.widget.TextView
import com.example.filler.gui.game.GameMediator

class Timer(private val context: GameMediator, var guiTimer: TextView) {

    private var remainingTime = 15

    fun start() {
        remainingTime = 15
        timer.start()
    }

    fun cancel() {
        timer.cancel()
    }

    private val timer = object : CountDownTimer(15000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            updateTimer()
        }

        override fun onFinish() {
            chooseColor()
        }
    }

    private fun updateTimer() {
        remainingTime--
        updateTimerTextView()
    }

    private fun updateTimerTextView() {
        val remainingTimeString = getString(remainingTime)
        guiTimer.text = remainingTimeString
    }

    private fun chooseColor() {
        context.aiTurn()
    }

    private fun getString(remainingTime: Int): String {
        return if (remainingTime < 10) {
            "00:0$remainingTime"
        } else {
            "00:$remainingTime"
        }
    }

}