package com.saddik.leanRecipes.utils.log

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC

class LogUtil(private val operationLevel: OperationLevel, private val entity: Class<*>) {

    private val logger: Logger = LoggerFactory.getLogger(entity::class.java)
    private val objectMapper = jacksonObjectMapper()

    fun log(baseLog: BaseLog) {

        try {
            baseLog.operationLevel = operationLevel
            baseLog.entity = entity.name

            // automatically add requestId to additionalInfo if it's missing
            val requestId = MDC.get("requestId")
            if (baseLog.additionalInfo == null) {
                baseLog.additionalInfo = mutableMapOf()
            }
            baseLog.additionalInfo?.put("requestId", requestId)


            val logText = objectMapper.writeValueAsString(baseLog)

            logger.info("$logText\n")

        } catch (e: Exception) {
            logger.error(e.message, e)
        }

    }

    fun logE(baseLog: BaseLog, e: Exception) {
        try {
            baseLog.operationLevel = operationLevel
            baseLog.entity = entity.name

            // automatically add requestId to additionalInfo if missing
            val requestId = MDC.get("requestId")
            if (baseLog.additionalInfo == null) {
                baseLog.additionalInfo = mutableMapOf()
            }
            baseLog.additionalInfo?.put("requestId", requestId)

            val logText = objectMapper.writeValueAsString(baseLog)

            logger.error("$logText\n", e)
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }

    fun logS(S: Any?) {
        try {
            logger.info("$S \n")
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }

}