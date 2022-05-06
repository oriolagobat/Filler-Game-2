package com.example.filler.log

import com.example.filler.constants.logic.GameConstants
import java.util.Date
import java.text.SimpleDateFormat
import java.util.*

object Logger {

    private val logList = mutableListOf<String>()

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun logD(message: String) {
        logList.add(getCurrentDateTime().toString(GameConstants.LOGGER_DATE_FORMAT) + " [DEBUG]: " + message)
    }

    fun logI(message: String) {
        logList.add(getCurrentDateTime().toString(GameConstants.LOGGER_DATE_FORMAT) + " [INFO]: " + message)
    }

    fun logW(message: String) {
        logList.add(getCurrentDateTime().toString(GameConstants.LOGGER_DATE_FORMAT) + " [WARN]: " + message)
    }

    fun logE(message: String) {
        logList.add(getCurrentDateTime().toString(GameConstants.LOGGER_DATE_FORMAT) + " [ERROR]: " + message)
    }

    fun getLog(): String {
        return logList.joinToString("\n")
    }
}