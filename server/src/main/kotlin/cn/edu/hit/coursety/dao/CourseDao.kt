package cn.edu.hit.coursety.dao

import cn.edu.hit.coursety.entity.domain.Course
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class CourseDao(val db: JdbcTemplate) {

    fun getCourseByCid(cid: String): Course? {
        val courses = db.query("SELECT * FROM courses WHERE cid=?", cid) { response, _ ->
            Course(response.getString("cid"), response.getString("cname"), response.getString("tid"))
        }
        return if (courses.isNotEmpty()) courses[0] else null
    }

}