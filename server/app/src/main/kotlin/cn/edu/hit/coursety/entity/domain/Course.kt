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
}
