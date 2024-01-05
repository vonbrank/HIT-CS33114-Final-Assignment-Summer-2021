package cn.edu.hit.coursety.exception

import org.springframework.http.HttpStatus

class AppException(message: String, val statusCode: HttpStatus) : Exception(message) {
    val status = if (statusCode.is4xxClientError) {
        "failed"
    } else {
        "error"
    }

}