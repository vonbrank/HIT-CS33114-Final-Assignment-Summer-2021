package cn.edu.hit.coursety.response

import java.time.LocalDateTime

sealed class Response(val code: Int, val description: String,
                      val timestamp: LocalDateTime = LocalDateTime.now(), val data: Any?)

class SuccessResponse(
        data: Any?,
        description: String = "OK",
        timestamp: LocalDateTime = LocalDateTime.now(),
) :
        Response(ResponseCode.SUCCESS.code, description, timestamp, data = data);

class FailResponse(description: String, timestamp: LocalDateTime = LocalDateTime.now()) :
        Response(ResponseCode.FAIL.code, description, timestamp, null)

class InternalErrorResponse(description: String, timestamp: LocalDateTime = LocalDateTime.now()) :
        Response(ResponseCode.INTERNAL_SERVER_ERROR.code, description, timestamp, null)