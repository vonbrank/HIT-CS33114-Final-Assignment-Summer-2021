package cn.edu.hit.coursety.exception

import cn.edu.hit.coursety.response.ErrorResponse
import cn.edu.hit.coursety.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class ExceptionControllerAdvice {
    @ExceptionHandler
    fun handleException(exception: Exception): ResponseEntity<Response> {

        return if (exception is AppException) {
            ResponseEntity(ErrorResponse(exception.message ?: "Unknown app error"), exception.statusCode)
        } else {
            ResponseEntity(ErrorResponse(exception.message ?: "Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }
}