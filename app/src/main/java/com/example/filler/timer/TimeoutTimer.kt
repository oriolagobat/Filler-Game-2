package com.example.filler.timer

import android.os.CountDownTimer
import android.widget.TextView
import com.example.filler.constants.logic.*
import com.example.filler.gui.game.GameMediator

class TimeoutTimer(
    private val context: GameMediator,
    override var timerTextView: TextView,
    difficulty: Difficulty
) : GameTimer {

    private var timeoutInSecs = 0L
    private var timeoutInMils = 0L
    private var remainingTime = 0

    override fun start() {
        resetRemainingTime()
        timer.start()
    }

    override fun init() {
        //  Not implemented since countdownTimer doesn't need to be initialized each round
    }

    override fun cancel() = timer.cancel()

    override fun finish() = cancel()

    init {
        timeoutInSecs = when (difficulty) {
            Difficulty.EASY -> EASY_MODE_TIMEOUT_SEC
            Difficulty.MEDIUM -> MEDIUM_MODE_TIMEOUT_SEC
            Difficulty.HARD -> HARD_MODE_TIMEOUT_SEC
        }
        timeoutInMils = timeoutInSecs * 1000
        resetRemainingTime()
    }

    private fun resetRemainingTime() {
        remainingTime = timeoutInSecs.toInt()
    }

    private val timer = object : CountDownTimer(timeoutInMils, TIMER_PERIOD_MS) {

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
}
