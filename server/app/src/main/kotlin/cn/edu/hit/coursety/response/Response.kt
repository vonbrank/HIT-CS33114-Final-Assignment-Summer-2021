package cn.edu.hit.coursety.response

import java.time.ZoneOffset
import java.time.ZonedDateTime

sealed class Response(
    val status: String,
    val timestamp: ZonedDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC)
)

class SuccessResponse<T>(
    val data: T?,
    status: String = "success",
    timestamp: ZonedDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC),
) :
    Response(status, timestamp)

class ErrorResponse(
    val message: String,
    status: String = "error",
    timestamp: ZonedDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC)
) :
    Response(status, timestamp)