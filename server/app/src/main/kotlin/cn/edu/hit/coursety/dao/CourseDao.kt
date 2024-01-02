package cn.edu.hit.coursety.dao

import cn.edu.hit.coursety.entity.domain.Course
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class CourseDao(val db: JdbcTemplate) {

    fun findAll(
        queryFilter: Map<String, String> = HashMap(),
        sortKeywords: List<String> = listOf(),
        limitOffset: Int = 0,
        limitRowCount: Int = 100
    ): List<Course> {

        var sql = "SELECT * FROM courses";
        var paramValues = arrayOf<String>()


        val conditions = queryFilter.keys.joinToString("AND ") { key -> "$key = ?" }

        if (conditions.isNotEmpty()) {
            sql += " WHERE $conditions"
            paramValues += queryFilter.values
        }

        val sorts = sortKeywords.map { keyword ->
            val isDescending = keyword.startsWith("-")
            val property = keyword.removePrefix("-")
            property to if (isDescending) "ASC" else "DESC"
        }
            .filter { (property, _) ->
                Course.dataBaseFieldName(property) != null
            }

        if (sorts.isNotEmpty()) {
            println(sorts)
            sql += " ORDER BY ${sorts.joinToString(", ") { (property, sortType) -> "${Course.dataBaseFieldName(property)} $sortType" }}"
        }

        sql += if (limitRowCount < 0) {
            " LIMIT $limitOffset, 100"
        } else {
            " LIMIT $limitOffset, $limitRowCount"
        }

        return db.query(sql, paramValues) { response, _ ->
            Course(response)
        }
    }

    fun findById(id: Int): Course? {
        val courses = db.query("SELECT * FROM courses WHERE id=?", id) { response, _ ->
            Course(response)
        }
        return if (courses.isNotEmpty()) courses[0] else null
    }

}