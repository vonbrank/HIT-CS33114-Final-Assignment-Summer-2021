package cn.edu.hit.coursety.dao

import cn.edu.hit.coursety.entity.domain.Course
import cn.edu.hit.coursety.entity.dto.CourseDto
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class CourseDao(val db: JdbcTemplate) {

    companion object {
        val dataBaseFieldNameMapper = mapOf(
            "id" to "id",
            "name" to "name",
            "courseCredit" to "course_credit",
            "creditHour" to "credit_hour",
            "department" to "department",
            "teacher" to "teacher"
        )

        fun String.dataClassFieldName(): String? {
            return dataBaseFieldNameMapper.entries.find { it.value == this }?.key
        }


    }


    fun findAll(
        featureQuerySql: String, featureParamValues: Array<String>
    ): List<Course> {

        val sql = "SELECT * FROM courses" + featureQuerySql
        println(sql)
        return db.query(sql, featureParamValues, courseMapRow)
    }

    fun findById(id: Int): Course? {
        val courses = db.query("SELECT * FROM courses WHERE id=?", arrayOf(id), courseMapRow)
        return if (courses.isNotEmpty()) courses[0] else null
    }

    fun create(courseDto: CourseDto): Int {
        val sql = """
            INSERT INTO courses(name, course_credit, credit_hour, department, teacher)
            VALUES(?, ?, ?, ?, ?)
        """.trimIndent()
        val keyHolder = GeneratedKeyHolder()
        db.update({ connection ->
            val preparedStatement = connection.prepareStatement(sql, arrayOf("id"))
            preparedStatement.setString(1, courseDto.name)
            preparedStatement.setInt(2, courseDto.courseCredit)
            preparedStatement.setInt(3, courseDto.creditHour)
            preparedStatement.setInt(4, courseDto.department)
            preparedStatement.setInt(5, courseDto.teacher)
            preparedStatement
        }, keyHolder)

        return keyHolder.key!!.toInt()
    }

    fun findByIdAndUpdate(id: Int, courseDto: CourseDto): Course? {
        val course = findById(id) ?: return null

        course.update(courseDto)

        val sql = """
            UPDATE courses
            SET name = ?, course_credit = ?, credit_hour = ?, department = ?, teacher = ?
            WHERE id = ?
        """.trimIndent()

        val paramValues =
            arrayOf(course.name, course.courseCredit, course.creditHour, course.department, course.teacher, course.id)

        db.update(sql, *paramValues)

        return findById(id)
    }

    fun findByIdAndDelete(id: Int): Course? {
        val course = findById(id) ?: return null
        val sql = """
            DELETE FROM courses
            WHERE id = ?
        """.trimIndent()
        val paramValues =
            arrayOf(id)

        return if (db.update(sql, *paramValues) == 1) {
            course
        } else {
            null
        }

    }

    private val courseMapRow = { rs: ResultSet, rowNum: Int ->
        Course(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("course_credit"),
            rs.getInt("credit_hour"),
            rs.getInt("department"),
            rs.getInt("teacher")
        )
    }
}
