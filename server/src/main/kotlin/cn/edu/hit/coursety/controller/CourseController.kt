package cn.edu.hit.coursety.controller

import cn.edu.hit.coursety.entity.domain.Score
import cn.edu.hit.coursety.entity.vo.StudentCourseScoreVo
import cn.edu.hit.coursety.service.ScoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CourseController(val scoreService: ScoreService) {
    @GetMapping("/course/getStudentCourseScore")
    fun getAllScore(): List<StudentCourseScoreVo> {
        return scoreService.getStudentScores("S021")
    }
}