package cn.edu.hit.coursety.interceptor

import cn.edu.hit.coursety.exception.AppException
import cn.edu.hit.coursety.service.AuthService
import cn.edu.hit.coursety.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ProtectInterceptor(val authService: AuthService, val userService: UserService) : BaseInterceptor() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if (!request.matchPatterns()) return true

        val authorization = request.getHeader("Authorization") ?: ""

        val authorizationSplit = authorization.split(" ")
        if (authorization.length >= 2) {
            val (_, token) = authorizationSplit
            val decodedJWT = authService.verifyToken(token) ?: throw AppException(
                "Token is invalid or has expired.",
                HttpStatus.UNAUTHORIZED
            )

            val id = decodedJWT.getClaim("id").toString().toInt()
            val issuedTime = decodedJWT.issuedAt
            val user = userService.getUserById(id)
            if (issuedTime.time < user.passwordChangedAt.time) {
                throw AppException("User recently changed password!. Please log in again.", HttpStatus.UNAUTHORIZED)
            }

            request.setAttribute("user", user)

        } else {
            throw AppException("You are not logged in! Please log in to get access.", HttpStatus.UNAUTHORIZED)
        }

        return true
    }
}