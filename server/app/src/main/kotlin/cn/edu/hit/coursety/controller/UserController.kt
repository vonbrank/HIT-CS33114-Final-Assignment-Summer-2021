package cn.edu.hit.coursety.controller

import cn.edu.hit.coursety.exception.AppException
import cn.edu.hit.coursety.response.ErrorResponse
import cn.edu.hit.coursety.response.Response
import cn.edu.hit.coursety.response.SuccessResponse
import cn.edu.hit.coursety.service.AuthService
import cn.edu.hit.coursety.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
class UserController(val userService: UserService, val authService: AuthService) {
    @GetMapping("")
    fun getAllUsers(@RequestHeader("Authorization") authorization: String?): ResponseEntity<Response> {

        authService.authorize(authorization ?: "")

        return ResponseEntity.ok(SuccessResponse(userService.getAllUser()))
    }

    @PostMapping("")
    fun createUsers(): ResponseEntity<Response> {
        throw AppException("This route is not yet defined", HttpStatus.BAD_REQUEST)
    }

    @GetMapping("{id}")
    fun getUser(@PathVariable id: String): ResponseEntity<Response> {
        val user = userService.getUserById(id.toInt())
        return ResponseEntity.ok(SuccessResponse(user))
    }

    @PatchMapping("{id}")
    fun updateUser(@PathVariable id: String): ResponseEntity<Response> {
        throw AppException("This route is not yet defined", HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<Response> {
        throw AppException("This route is not yet defined", HttpStatus.BAD_REQUEST)
    }

}