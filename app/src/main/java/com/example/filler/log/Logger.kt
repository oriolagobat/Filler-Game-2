package com.example.filler.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.Date
import java.text.SimpleDateFormat
import java.util.*

object Logger {

    private val logger: Logger = LoggerFactory.getLogger("")
    private val logList = mutableListOf<String>()

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun logD(message: String) {
        logList.add(getCurrentDateTime().toString("HH:mm:ss") + " [DEBUG]: " + message)
    }

    fun logI(message: String) {
        logList.add(getCurrentDateTime().toString("HH:mm:ss") + " [INFO]: " + message)
    }

    fun logW(message: String) {
        logList.add(getCurrentDateTime().toString("HH:mm:ss") + " [WARN]: " + message)
    }

    fun logE(message: String) {
        logList.add(getCurrentDateTime().toString("HH:mm:ss") + " [ERROR]: " + message)
    }

    fun getLog(): String {
        return logList.joinToString("\n")
    }
}