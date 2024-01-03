package cn.edu.hit.coursety.service

import cn.edu.hit.coursety.dao.CourseDao
import cn.edu.hit.coursety.entity.domain.Course
import cn.edu.hit.coursety.entity.dto.CourseDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import kotlin.math.max

@Service
class CourseService(val courseDao: CourseDao) {
    fun getAllCourses(query: Map<String, String> = HashMap()): List<Map<*, *>> {
        val queryFilter = query.filterKeys {
            it !in listOf("page", "sort", "limit", "fields")
        }

        val sort = query["sort"]
        val sortKeywords = sort?.split(",") ?: listOf()

        val page = query["page"]?.toIntOrNull() ?: 0;
        val limit = query["limit"]?.toIntOrNull() ?: 100;
        val offset = max((page - 1) * limit, 0)

        val objectMapper = jacksonObjectMapper()
        val courses = courseDao.findAll(queryFilter, sortKeywords, offset, limit)
        var courseMaps = courses.map { course ->
            val courseMap = objectMapper.convertValue(course, Map::class.java)
            courseMap
        }

        val limitFields = query["fields"]
        limitFields?.let {
            val fields = limitFields.split(",")
            courseMaps = courseMaps.map { courseMap ->
                val newCourseMap = courseMap.filterKeys { it in fields }
                newCourseMap
            }
        }

        return courseMaps
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