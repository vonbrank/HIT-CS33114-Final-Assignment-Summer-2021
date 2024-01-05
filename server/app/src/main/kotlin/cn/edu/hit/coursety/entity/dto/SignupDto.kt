package cn.edu.hit.coursety.entity.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignupDto(
    @field:NotBlank(message = "Course name cannot be blank")
    val firstName: String,
    @field:NotBlank(message = "Course name cannot be blank")
    val lastName: String,
    @field:Email
    val email: String,
    @field:NotBlank(message = "Course name cannot be blank")
    var password: String,
    @field:NotBlank(message = "Course name cannot be blank")
    val passwordConfirm: String,
    val role: String,
    val department: Int
)
