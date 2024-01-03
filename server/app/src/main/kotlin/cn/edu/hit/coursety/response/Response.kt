package cn.edu.hit.coursety.response

import java.time.LocalDateTime

sealed class Response(
    val status: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)

class SuccessResponse<T>(
    val data: T?,
    status: String = "success",
    timestamp: LocalDateTime = LocalDateTime.now(),
) :
    Response(status, timestamp)

class ErrorResponse(val message: String, status: String = "error", timestamp: LocalDateTime = LocalDateTime.now()) :
    Response(status, timestamp)