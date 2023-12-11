package cn.edu.hit.coursety.dao

import cn.edu.hit.coursety.entity.domain.User
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class UserDao(val db: JdbcTemplate) {
    fun getUserById(id: String): User? {
        val users = db.query("SELECT * FROM users WHERE userid=?", id) { response, _ ->
            User(response.getString("userid"), response.getString("name"),
                    response.getString("gender"), response.getString("major"),
                    response.getString("profession"),
                    response.getInt("userType"))
        }
        return if (users.isNotEmpty()) users[0] else null
    }

    fun getUserByIdAndPassword(id: String, password: String): User? {
        val users = db.query("SELECT * FROM users WHERE userid=? AND password=?", id, password) { response, _ ->
            User(response.getString("userid"), response.getString("name"),
                    response.getString("gender"), response.getString("major"),
                    response.getString("profession"),
                    response.getInt("userType"))
        }
        return if (users.isNotEmpty()) users[0] else null
    }
}