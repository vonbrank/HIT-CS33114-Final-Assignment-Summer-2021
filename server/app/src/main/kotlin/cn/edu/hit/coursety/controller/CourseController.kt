package cn.edu.hit.coursety.controller

import cn.edu.hit.coursety.entity.dto.CourseDto
import cn.edu.hit.coursety.response.Response
import cn.edu.hit.coursety.response.ErrorResponse
import cn.edu.hit.coursety.response.SuccessResponse
import cn.edu.hit.coursety.service.CourseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/courses")
@CrossOrigin
class CourseController(val courseService: CourseService) {
    @GetMapping("")
    fun getAllCourses(@RequestParam query: Map<String, String>): ResponseEntity<Response> {
        return ResponseEntity.ok(SuccessResponse(courseService.getAllCourses(query)))
    }

    @PostMapping("")
    fun createCourse(@Valid @RequestBody courseDto: CourseDto): ResponseEntity<Response> {
        val course = courseService.createCourse(courseDto)
        return ResponseEntity.ok(SuccessResponse(course))
    }

    @GetMapping("{id}")
    fun getCourse(@PathVariable id: String): ResponseEntity<Response> {
        val course = courseService.getCourse(id.toInt())
        return if (course == null) {
            ResponseEntity(ErrorResponse("No course found with that ID."), HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(SuccessResponse(course))
        }
    }

    @PatchMapping("{id}")
    fun updateCourse(
        @PathVariable id: String,
        @Valid @RequestBody courseDto: CourseDto
    ): ResponseEntity<Response> {
        val course = courseService.updateCourse(id.toInt(), courseDto)
        return if (course == null) {
            ResponseEntity(ErrorResponse("No course found with that ID."), HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(SuccessResponse(course))
        }
    }

    @DeleteMapping("{id}")
    fun deleteCourse(@PathVariable id: String): ResponseEntity<Response> {
        val result = courseService.deleteCourse(id.toInt())
        return if (result) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(ErrorResponse("No course found with that ID."), HttpStatus.NOT_FOUND)
        }
    }
}