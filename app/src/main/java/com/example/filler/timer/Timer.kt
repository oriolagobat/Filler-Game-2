package com.example.filler.timer

import android.app.Activity
import android.os.CountDownTimer
import androidx.core.content.contentValuesOf
import com.example.filler.gui.game.GameMediator

class Timer(private val context: GameMediator) {

    private var remainingTime = 15

    fun start() {
        remainingTime = 15
        timer.start()
    }

    private val timer = object : CountDownTimer(10000, 1000) {

        override fun onTick(millisUntilFinished: Long) { updateTimer() }

        override fun onFinish() { chooseColor() }
    }

    private fun updateTimer() {
        remainingTime--
    }

    private fun chooseColor() {
        context.aiTurn()
    }

}