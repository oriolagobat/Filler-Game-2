package com.example.filler.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Logger {

    private val logger: Logger = LoggerFactory.getLogger("")
    var gameLog = ""

    fun logD(message: String) {
        logger.debug(message)
    }

    fun logI(message: String) {
        logger.info(message)
    }

    fun logW(message: String) {
        logger.warn(message)
    }

    fun logE(message: String) {
        logger.error(message)
    }

    // Read /tmp/logback.log and return it as a String
    fun getLog(): String {
        return "TODO"
        java.io.File("/tmp/logback.log").readText()
    }
}