package cn.edu.hit.coursety.entity.domain

import java.sql.ResultSet

data class Course(
    val id: Int,
    val name: String,
    val courseCredit: Int,
    val creditHour: Int,
    val department: Int,
    val teacher: Int
) {
    constructor(response: ResultSet) : this(
        response.getInt("id"),
        response.getString("name"),
        response.getInt("course_credit"),
        response.getInt("credit_hour"),
        response.getInt("department"),
        response.getInt("teacher")
    ) {

    }

    companion object {
        fun dataBaseFieldName(propertyName: String): String? {
            return when (propertyName) {
                "id" -> "id"
                "name" -> "name"
                "courseCredit" -> "course_credit"
                "creditHour" -> "credit_hour"
                "department" -> "department"
                "teacher" -> "teacher"
                else -> null
            }
        }
    }
}
