package cn.edu.hit.coursety.dao

import cn.edu.hit.coursety.entity.domain.User
import cn.edu.hit.coursety.entity.domain.UserRole
import cn.edu.hit.coursety.entity.dto.SignupDto
import cn.edu.hit.coursety.exception.AppException
import org.springframework.http.HttpStatus
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
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
                "password" to "password",
                "role" to "role",
                "department" to "department"
            )
    }

    fun findAll(): List<User> {
        val sql = "SELECT * FROM users"
        return db.query(sql, mapRow)
    }

    fun findById(id: Int): User {
        val sql = "SELECT * FROM users WHERE id=?"
        val users = db.query(sql, arrayOf(id), mapRow)
        if (users.isEmpty()) {
            throw AppException("No user found with this ID.", HttpStatus.NOT_FOUND)
        }
        return users[0]
    }

    fun findByEmail(email: String): User {
        val sql = "SELECT * FROM users WHERE email=?"
        val users = db.query(sql, arrayOf(email), mapRow)
        if (users.isEmpty()) {
            throw AppException("No user found with this email.", HttpStatus.NOT_FOUND)
        }
        return users[0]
    }

    fun create(signupDto: SignupDto): Int {
        val sql = """
            INSERT INTO users(first_name, last_name, email, password, role, department)
            VALUES(?, ?, ?, ?, ?, ?)
        """.trimIndent()
        val keyHolder = GeneratedKeyHolder()
        db.update({ connection ->
            val preparedStatement = connection.prepareStatement(sql, arrayOf("id"))
            preparedStatement.setString(1, signupDto.firstName)
            preparedStatement.setString(2, signupDto.lastName)
            preparedStatement.setString(3, signupDto.email)
            preparedStatement.setString(4, signupDto.password)
            preparedStatement.setString(5, signupDto.role)
            preparedStatement.setInt(6, signupDto.department)
            preparedStatement
        }, keyHolder)

        return keyHolder.key!!.toInt()
    }

    override val mapRow: (ResultSet, Int) -> User
        get() = { rs, rowNum ->
            User(
                rs.getInt("id".dataBaseFieldName()),
                rs.getString("firstName".dataBaseFieldName()),
                rs.getString("lastName".dataBaseFieldName()),
                rs.getString("email".dataBaseFieldName()),
                rs.getString("password".dataBaseFieldName()),
                UserRole.roleValueOf(rs.getString("role".dataBaseFieldName()))!!,
                rs.getInt("department".dataBaseFieldName())
            )
        }

}