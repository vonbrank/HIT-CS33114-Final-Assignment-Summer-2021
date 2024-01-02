package cn.edu.hit.coursety.controller

import cn.edu.hit.coursety.response.BaseResponse
import cn.edu.hit.coursety.response.ErrorResponse
import cn.edu.hit.coursety.response.Response
import cn.edu.hit.coursety.service.CourseService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/courses")
@CrossOrigin
class CourseController(val courseService: CourseService) {
    @GetMapping("")
    fun getAllCourses(@RequestParam query: Map<String, String>): ResponseEntity<BaseResponse> {
        return ResponseEntity.ok(Response(courseService.getAllCourses(query)))
    }

    @GetMapping("{id}")
    fun getCourse(@PathVariable id: String): ResponseEntity<BaseResponse> {
        val course = courseService.getCourse(id.toInt())
        return if (course == null) {
            ResponseEntity(ErrorResponse("No course found with that ID."), HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(Response(course))
        }

    }
}