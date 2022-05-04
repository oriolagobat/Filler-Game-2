package com.example.filler.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Logger {

    private val logger: Logger = LoggerFactory.getLogger("")

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
}