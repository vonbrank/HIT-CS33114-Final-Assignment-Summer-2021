package cn.edu.hit.coursety.dao

import cn.edu.hit.coursety.entity.domain.User
import cn.edu.hit.coursety.entity.domain.UserRole
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class UserDao(val db: JdbcTemplate) : IDao<User> {

    companion object : IDaoCompanionObject {
        override val dataBaseFieldNameMapper
            get() = mapOf(
                "id" to "id",
                "firstName" to "first_name",
                "lastName" to "last_name",
                "email" to "email",
                "role" to "role",
                "department" to "department"
            )
    }

    fun findAll(): List<User> {
        val sql = "SELECT * FROM users"
        return db.query(sql, mapRow)
    }

    fun findById(id: Int): User? {
        val sql = "SELECT * FROM users WHERE id=?"
        val users = db.query(sql, arrayOf(id), mapRow)
        return if (users.isNotEmpty()) users[0] else null
    }

    override val mapRow: (ResultSet, Int) -> User
        get() = { rs, rowNum ->
            User(
                rs.getInt("id".dataBaseFieldName()),
                rs.getString("firstName".dataBaseFieldName()),
                rs.getString("lastName".dataBaseFieldName()),
                rs.getString("email".dataBaseFieldName()),
                enumValues<UserRole>().find { it.value == rs.getString("role".dataBaseFieldName()) }!!,
                rs.getInt("department".dataBaseFieldName())
            )
        }

}