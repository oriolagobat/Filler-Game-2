package com.example.filler.timer

import android.os.CountDownTimer
import android.widget.TextView
import com.example.filler.constants.logic.*
import com.example.filler.gui.game.GameMediator

class Timer(private val context: GameMediator, var guiTimer: TextView, val difficulty: Difficulty) {

    private var timoutInSecs = 0L
    private var timoutInMilis = 0L
    private var remainingTime = 0

    init {
        timoutInSecs = when (difficulty) {
            Difficulty.EASY -> EASY_MODE_TIMEOUT_SEC
            Difficulty.MEDIUM -> MEDIUM_MODE_TIMEOUT_SEC
            Difficulty.HARD -> HARD_MODE_TIMEOUT_SEC
        }
        timoutInMilis = timoutInSecs * 1000
        resetRamainingTime()
    }

    private fun resetRamainingTime() {
        remainingTime = timoutInSecs.toInt()
    }


    fun start() {
        resetRamainingTime()
        timer.start()
    }

    fun cancel() {
        timer.cancel()
    }

    private val timer = object : CountDownTimer(timoutInMilis, TIMER_PERIOD_MS) {

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