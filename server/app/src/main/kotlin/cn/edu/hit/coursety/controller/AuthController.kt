package cn.edu.hit.coursety.controller

//import cn.edu.hit.coursety.entity.vo.UserLoginVo
import cn.edu.hit.coursety.entity.dto.LoginDto
import cn.edu.hit.coursety.entity.dto.SignupDto
import cn.edu.hit.coursety.response.Response
import cn.edu.hit.coursety.response.SuccessResponse
import cn.edu.hit.coursety.service.AuthService
import cn.edu.hit.coursety.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
class AuthController(val userService: UserService, val authService: AuthService) {

    @PostMapping("signup")
    fun signup(@RequestBody signupDto: SignupDto): ResponseEntity<Response> {
        val user = authService.signup(signupDto)
        return ResponseEntity.ok(SuccessResponse(user))
    }

    @CrossOrigin
    @PostMapping("login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<Response> {
        val user = authService.login(loginDto)
        return ResponseEntity.ok(SuccessResponse(user))
    }
}