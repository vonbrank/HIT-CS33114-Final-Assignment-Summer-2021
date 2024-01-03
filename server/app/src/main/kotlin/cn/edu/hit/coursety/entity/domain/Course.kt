package cn.edu.hit.coursety.entity.domain

import cn.edu.hit.coursety.entity.dto.CourseDto
import java.sql.ResultSet

data class Course(
    val id: Int,
    var name: String,
    var courseCredit: Int,
    var creditHour: Int,
    var department: Int,
    var teacher: Int
) {
    fun update(courseDto: CourseDto): Course {
        this.name = courseDto.name
        this.courseCredit = courseDto.courseCredit
        this.creditHour = courseDto.creditHour
        this.department = courseDto.department
        this.teacher = courseDto.teacher
        return this
    }
}
