package cn.edu.hit.coursety.controller

import cn.edu.hit.coursety.service.Score
import cn.edu.hit.coursety.service.ScoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CourseController(val scoreService: ScoreService) {
    @GetMapping("/course/getAllScore")
    fun getAllScore(): List<Score> {
        return scoreService.findScores()
    }
}