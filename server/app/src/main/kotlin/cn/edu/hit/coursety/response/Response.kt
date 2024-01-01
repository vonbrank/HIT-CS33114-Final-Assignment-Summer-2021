package cn.edu.hit.coursety.response

import java.time.LocalDateTime

sealed class BaseResponse(
    val status: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)

class Response<T>(
    val data: T?,
    status: String = "success",
    timestamp: LocalDateTime = LocalDateTime.now(),
) :
    BaseResponse(status, timestamp)

class ErrorResponse(val message: String, status: String = "error", timestamp: LocalDateTime = LocalDateTime.now()) :
    BaseResponse(status, timestamp)