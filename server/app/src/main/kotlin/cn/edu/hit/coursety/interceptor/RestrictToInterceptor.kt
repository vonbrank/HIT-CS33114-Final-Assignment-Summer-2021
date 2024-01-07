package cn.edu.hit.coursety.interceptor

import cn.edu.hit.coursety.entity.domain.User
import cn.edu.hit.coursety.entity.domain.UserRole
import cn.edu.hit.coursety.exception.AppException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class RestrictToInterceptor : BaseInterceptor() {

    private var roles: List<UserRole> = listOf()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if (!request.matchPatterns()) return true

        val user = request.getAttribute("user") as User?
            ?: throw AppException("You do not have permission to perform this action", HttpStatus.FORBIDDEN)

        if (!roles.contains(user.role)) {
            throw AppException("You do not have permission to perform this action", HttpStatus.FORBIDDEN)
        }

//        println(user)

        return true
    }

    fun setRoles(vararg roles: UserRole): RestrictToInterceptor {

        this.roles = roles.map { it }

        return this
    }
}