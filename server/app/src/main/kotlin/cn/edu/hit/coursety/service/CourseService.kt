package cn.edu.hit.coursety.service

import cn.edu.hit.coursety.dao.CourseDao
import cn.edu.hit.coursety.entity.domain.Course
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CourseService(val courseDao: CourseDao) {
    fun getAllCourses(): List<Course> {
        return courseDao.findAll()
    }

    fun getCourse(id: Int): Course? {
        return courseDao.findById(id)
    }
}