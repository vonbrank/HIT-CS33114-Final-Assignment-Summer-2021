package cn.edu.hit.coursety.controller

import cn.edu.hit.coursety.entity.domain.Score
import cn.edu.hit.coursety.entity.vo.StudentCourseScoreVo
import cn.edu.hit.coursety.response.Response
import cn.edu.hit.coursety.response.SuccessResponse
import cn.edu.hit.coursety.service.ScoreService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CourseController(val scoreService: ScoreService) {

    @CrossOrigin
    @GetMapping("api/course/getStudentCourseScore")
    @ResponseBody
    fun getAllScore(): Response {
        return SuccessResponse(scoreService.getStudentScores("S021"));
    }
}