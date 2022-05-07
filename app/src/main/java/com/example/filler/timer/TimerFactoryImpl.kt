package com.example.filler.timer

import android.widget.TextView
import com.example.filler.gui.game.GameMediator
import com.example.filler.logic.game.GameSettings

class TimerFactoryImpl(
    private val gameMediator: GameMediator,
    private val timerTextView: TextView,
    private val gameSettings: GameSettings
) :
    TimerFactory {
    override fun createTimer(): GameTimer {
        return when (gameSettings.hasTimeout) {
            true -> TimeoutTimer(gameMediator, timerTextView, gameSettings.difficulty)
            false -> ChronoTimer(timerTextView, gameSettings.difficulty)
        }
    }

}
