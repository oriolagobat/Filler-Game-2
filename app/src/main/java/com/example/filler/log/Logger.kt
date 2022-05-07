package com.example.filler.log

import com.example.filler.constants.logic.LOGGER_DATE_FORMAT
import java.util.Date
import java.text.SimpleDateFormat
import java.util.*

object Logger {

    private val logList = mutableListOf<String>()

    fun logDebug(message: String) {
        logList.add(getCurrentDateTime().toString(LOGGER_DATE_FORMAT) + " [DEBUG]: " + message)
    }

    fun logInfo(message: String) {
        logList.add(getCurrentDateTime().toString(LOGGER_DATE_FORMAT) + " [INFO]: " + message)
    }

    fun logWarning(message: String) {
        logList.add(getCurrentDateTime().toString(LOGGER_DATE_FORMAT) + " [WARN]: " + message)
    }

    fun logError(message: String) {
        logList.add(getCurrentDateTime().toString(LOGGER_DATE_FORMAT) + " [ERROR]: " + message)
    }

    fun clearLog() = logList.clear()

    fun getLog(): String {
        return logList.joinToString("\n")
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}
