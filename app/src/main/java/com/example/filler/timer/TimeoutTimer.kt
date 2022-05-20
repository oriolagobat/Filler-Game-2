/*
package com.example.filler.timer

import android.os.CountDownTimer
import android.widget.TextView
import com.example.filler.constants.logic.*
//import com.example.filler.gui.game.GameMediator

class TimeoutTimer(
    private val context: GameMediator,
    override var timerTextView: TextView,
    val difficulty: Difficulty
) : GameTimer {

    private var timoutInSecs = 0L
    private var timoutInMilis = 0L
    private var remainingTime = 0

    override fun start() {
        resetRamainingTime()
        timer.start()
    }

    //  Not implemented since countdowntimer doesn't need to be initialized each round
    override fun init() { }

    override fun cancel() = timer.cancel()

    override fun finish() = cancel()

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

    private val timer = object : CountDownTimer(timoutInMilis, TIMER_PERIOD_MS) {

        override fun onTick(millisUntilFinished: Long) = updateTimer()

        override fun onFinish() = context.playerTurnByTimeout()
    }

    private fun updateTimer() {
        remainingTime--
        updateTimerTextView()
    }

    private fun updateTimerTextView() {
        val remainingTimeString = intToFormattedTime(remainingTime)
        timerTextView.text = remainingTimeString
    }
}*/
