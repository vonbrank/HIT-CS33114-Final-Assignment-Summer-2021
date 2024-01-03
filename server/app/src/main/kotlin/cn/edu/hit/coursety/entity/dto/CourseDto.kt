package cn.edu.hit.coursety.entity.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class CourseDto(
    @field:NotBlank(message = "Course name cannot be blank")
    val name: String,
    @field:Min(1)
    val courseCredit: Int,
    @field:Min(1)
    val creditHour: Int,
    val department: Int,
    val teacher: Int
)
