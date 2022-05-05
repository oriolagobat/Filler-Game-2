package com.example.filler.timer

import android.os.CountDownTimer
import android.widget.TextView
import com.example.filler.gui.game.GameMediator

class Timer(private val context: GameMediator, private val guiTimer: TextView) {

    private var remainingTime = 15

    fun start() {
        remainingTime = 15
        timer.start()
    }

    private val timer = object : CountDownTimer(10000, 1000) {

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
        val remainingTimeString = "00:$remainingTime"
        guiTimer.text = remainingTimeString
    }

    private fun chooseColor() {
        context.aiTurn()
    }

}