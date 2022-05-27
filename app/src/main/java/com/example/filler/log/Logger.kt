package com.example.filler.log

import androidx.lifecycle.MutableLiveData
import com.example.filler.constants.logic.LOGGER_DATE_FORMAT
import java.util.Date
import java.text.SimpleDateFormat
import java.util.*

object Logger {
    val logList = MutableLiveData<List<String>>()

    init {
        if (logList.value == null) {
            logList.value = listOf<String>()
        }
    }

    fun logDebug(message: String) {
        logList.value = logList.value?.plus(newLogMessage(message, "DEBUG"))
    }

    fun logInfo(message: String) {
        logList.value = logList.value?.plus(newLogMessage(message, "INFO"))
    }

    fun logWarning(message: String) {
        logList.value = logList.value?.plus(newLogMessage(message, "WARNING"))
    }

    fun logError(message: String) {
        logList.value = logList.value?.plus(newLogMessage(message, "ERROR"))
    }

    fun clearLog() {
        logList.value = listOf()
    }

    fun getLog(): String {
        return logList.value!!.joinToString("\n")
    }

    private fun newLogMessage(message: String, type: String): String {
        return getCurrentDateTime().toString(LOGGER_DATE_FORMAT) + " [$type]: " + message
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}
