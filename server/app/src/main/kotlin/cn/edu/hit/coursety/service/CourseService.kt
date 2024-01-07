package cn.edu.hit.coursety.service

import cn.edu.hit.coursety.dao.CourseDao
import cn.edu.hit.coursety.entity.domain.Course
import cn.edu.hit.coursety.entity.dto.CourseDto
import cn.edu.hit.coursety.utils.ApiFeatures
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import kotlin.math.max

@Service
class CourseService(val courseDao: CourseDao) {
    fun getAllCourses(query: Map<String, String> = HashMap()): List<Map<*, *>> {
        return ApiFeatures<Course>(query, courseDao.dataBaseFieldNameMapper).filter().sort().paginate().limitFields()
            .execute { featureQuerySql, featureParamValues ->
                courseDao.findAll(featureQuerySql, featureParamValues)
            }.queryResultMaps
    }

    fun createCourse(courseDto: CourseDto): Course {
        val id = courseDao.create(courseDto)
        return courseDao.findById(id)!!
    }

    fun getCourse(id: Int): Course? {
        return courseDao.findById(id)
    }

    fun updateCourse(id: Int, courseDto: CourseDto): Course? {
        return courseDao.findByIdAndUpdate(id, courseDto)
    }

    fun deleteCourse(id: Int): Boolean {
        return courseDao.findByIdAndDelete(id) != null
    }
}