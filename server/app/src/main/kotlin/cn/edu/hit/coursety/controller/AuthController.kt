package cn.edu.hit.coursety.controller

import cn.edu.hit.coursety.entity.domain.User
import cn.edu.hit.coursety.entity.dto.*
import cn.edu.hit.coursety.entity.vo.ForgotPasswordVo
import cn.edu.hit.coursety.response.Response
import cn.edu.hit.coursety.response.SuccessResponse
import cn.edu.hit.coursety.service.AuthService
import cn.edu.hit.coursety.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
class AuthController(val userService: UserService, val authService: AuthService) {

    @PostMapping("signup")
    fun signup(@RequestBody signupDto: SignupDto): ResponseEntity<Response> {
        val user = authService.signup(signupDto)
        return ResponseEntity.ok(SuccessResponse(user))
    }

    @PostMapping("login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<Response> {
        val loginVo = authService.login(loginDto)
        return ResponseEntity.ok(SuccessResponse(loginVo))
    }

    @PostMapping("forgotPassword")
    fun forgotPassword(@RequestBody forgotPasswordDto: ForgotPasswordDto): ResponseEntity<Response> {
        val resetToken = authService.createPasswordResetToken(forgotPasswordDto.email)
        return ResponseEntity.ok(SuccessResponse(ForgotPasswordVo("PATCH /api/v1/users/resetPassword/$resetToken")))
    }

    @PatchMapping("resetPassword/{token}")
    fun forgotPassword(
        @RequestBody resetPasswordDto: ResetPasswordDto,
        @PathVariable token: String
    ): ResponseEntity<Response> {
        val loginVo = authService.resetPassword(token, resetPasswordDto)
        return ResponseEntity(SuccessResponse(loginVo), HttpStatus.CREATED)
    }

    @PostMapping("updatePassword")
    fun updatePassword(
        @RequestBody updatePasswordDto: UpdatePasswordDto,
        @RequestAttribute("user") user: User
    ): ResponseEntity<Response> {
        val newUser = authService.updatePassword(user.id, updatePasswordDto)
        return ResponseEntity.ok(SuccessResponse(newUser))
    }
}