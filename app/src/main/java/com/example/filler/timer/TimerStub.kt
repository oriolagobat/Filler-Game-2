package com.example.filler.timer

import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import com.example.filler.gui.game.GameMediator

class TimerStub() {

    private var remainingTime = 15

    fun start() {
        remainingTime = 15
        timer.start()
    }

    fun stop() {
        timer.cancel()
    }

    private val timer = object : CountDownTimer(10000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            Log.d("Timer", "Tick")
        }

        override fun onFinish() {
            Log.d(  "Timer", "Finish")
        }
    }
}