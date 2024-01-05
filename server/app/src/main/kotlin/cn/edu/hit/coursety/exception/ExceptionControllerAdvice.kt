package cn.edu.hit.coursety.exception

import cn.edu.hit.coursety.response.ErrorResponse
import cn.edu.hit.coursety.response.Response
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class ExceptionControllerAdvice {

    fun MethodArgumentNotValidException.handle(): ResponseEntity<Response> {
        return ResponseEntity(
            ErrorResponse(
                "Invalid input data. ${
                    this.fieldErrors.map { "${it.field} ${it.defaultMessage}" }.joinToString(". ")
                }"
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    fun handleJsonParseError(): ResponseEntity<Response> {
        return ResponseEntity(
            ErrorResponse(
                "Cannot parse JSON."
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    fun Exception.handle(): ResponseEntity<Response> {
        this.printStackTrace()
        return ResponseEntity(
            ErrorResponse(this.message ?: "Unknown error"),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler
    fun handleException(exception: Exception): ResponseEntity<Response> {

        return when (exception) {
            is AppException -> ResponseEntity(
                ErrorResponse(exception.message ?: "Unknown app error"),
                exception.statusCode
            )

            is MethodArgumentNotValidException -> exception.handle()
            is HttpMessageNotReadableException -> {
                when (exception.cause) {
                    is JsonParseException, is MismatchedInputException -> handleJsonParseError()
                    else -> exception.handle()
                }
            }

            else -> exception.handle()
        }

    }
}