package com.saddik.leanRecipes.exceptions

import com.saddik.leanRecipes.utils.dto.ErrorResponseDto
import com.saddik.leanRecipes.utils.log.LogUtil
import com.saddik.leanRecipes.utils.log.OperationLevel
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import java.util.function.Consumer

@ControllerAdvice
class GenericExceptions {

    val logUtil = LogUtil(OperationLevel.`EXCEPTION-HANDLERS`, this::class.java)

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException): ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto()
        errorResponse.message = exception.message
        errorResponse.requestid = MDC.get("requestId")
        errorResponse.timestamp = Instant.now()

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }


    @ExceptionHandler(ResourceAlreadyExistsException::class)
    fun handleResourceAlreadyExistsException(exception: ResourceAlreadyExistsException): ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto()
        errorResponse.message = exception.message
        errorResponse.requestid = MDC.get("requestId")
        errorResponse.timestamp = Instant.now()

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse)
    }

    @ExceptionHandler
    fun handleGlobalExceptions(exception: Exception): ResponseEntity<ErrorResponseDto> {
        exception.printStackTrace() // Prints the stack trace to the console

        val errorResponse = ErrorResponseDto()
        errorResponse.message = exception.cause.toString()
        errorResponse.requestid = MDC.get("requestId")
        errorResponse.timestamp = Instant.now()

        logUtil.logS("I am global exception::: invoked")

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponseDto> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.fieldErrors.forEach(Consumer { error: FieldError ->
            errors[error.field] = error.defaultMessage
        })

        val errorResponse = ErrorResponseDto()
        errorResponse.requestid = MDC.get("requestId")
        errorResponse.timestamp = Instant.now()
        errorResponse.data = errors

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }


}