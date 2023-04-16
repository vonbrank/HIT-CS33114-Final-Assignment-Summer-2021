package cn.edu.hit.coursety.controller

import cn.edu.hit.coursety.entity.dto.LoginDto
import cn.edu.hit.coursety.entity.vo.UserLoginVo
import cn.edu.hit.coursety.response.*
import cn.edu.hit.coursety.service.UserService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(val userService: UserService) {
    @CrossOrigin
    @PostMapping("api/user/login")
    @ResponseBody
    fun login(@RequestBody loginDto: LoginDto): Response {
        val authorization = userService.checkUserAuthorization(loginDto.userId, loginDto.password)

        if (authorization) {
            val user = userService.getUserById(loginDto.userId)
                    ?: return InternalErrorResponse("Internal server error.")
            return SuccessResponse(UserLoginVo.fromUser(user))
        }

        return FailResponse("User does not exist.")
    }
}