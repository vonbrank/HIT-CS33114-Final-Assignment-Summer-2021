package cn.edu.hit.coursety.controller

import cn.edu.hit.coursety.response.ErrorResponse
import cn.edu.hit.coursety.response.Response
import cn.edu.hit.coursety.response.SuccessResponse
import cn.edu.hit.coursety.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
class UserController(val userService: UserService) {
    @GetMapping("")
    fun getAllUsers(): ResponseEntity<Response> {
        return ResponseEntity.ok(SuccessResponse(userService.getAllUser()))
    }

    @PostMapping("")
    fun createUsers(): ResponseEntity<Response> {
        return ResponseEntity(ErrorResponse("This route is not yet defined"), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @GetMapping("{id}")
    fun getUser(@PathVariable id: String): ResponseEntity<Response> {
        val user = userService.getUserById(id.toInt())
        return if (user == null) {
            ResponseEntity(ErrorResponse("No user found with this ID."), HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(SuccessResponse(user))
        }
    }

    @PatchMapping("{id}")
    fun updateUser(@PathVariable id: String): ResponseEntity<Response> {
        return ResponseEntity(ErrorResponse("This route is not yet defined"), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<Response> {
        return ResponseEntity(ErrorResponse("This route is not yet defined"), HttpStatus.INTERNAL_SERVER_ERROR)
    }

}