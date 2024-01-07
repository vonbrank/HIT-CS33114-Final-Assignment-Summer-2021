package cn.edu.hit.coursety.dao

import cn.edu.hit.coursety.entity.domain.User
import cn.edu.hit.coursety.entity.domain.UserRole
import cn.edu.hit.coursety.entity.dto.SignupDto
import cn.edu.hit.coursety.exception.AppException
import org.springframework.http.HttpStatus
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.Date
import java.sql.ResultSet
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Repository
class UserDao(val db: JdbcTemplate) : BaseDao<User>() {

    override val dataBaseFieldNameMapper: Map<String, String>
        get() = mapOf(
            "id" to "id",
            "firstName" to "first_name",
            "lastName" to "last_name",
            "email" to "email",
            "password" to "password",
            "role" to "role",
            "department" to "department",
            "passwordChangedAt" to "password_changed_at",
            "passwordResetToken" to "password_reset_token",
            "passwordResetExpires" to "password_reset_expires"
        )

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

    fun findByResetToken(resetToken: String): User {
        val sql = "SELECT * FROM users WHERE password_reset_token=?"
        val users = db.query(sql, arrayOf(resetToken), mapRow)
        if (users.isEmpty()) {
            throw AppException("Token is invalid or has expired.", HttpStatus.BAD_REQUEST)
        }
        return users[0]
    }

    fun create(signupDto: SignupDto): Int {
        val sql = """
            INSERT INTO users(first_name, last_name, email, password, role, department, password_changed_at)
            VALUES(?, ?, ?, ?, ?, ?, ?)
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
            preparedStatement.setString(7, ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).toString())
            preparedStatement
        }, keyHolder)

        return keyHolder.key!!.toInt()
    }

    fun save(user: User): User {
        val id = user.id
        val sql = """
            UPDATE users
            SET first_name = ?, last_name = ?, email = ?, password = ?, role = ?, department = ?, 
            password_changed_at = ?, 
            password_reset_token = ?,
            password_reset_expires = ?
            WHERE id = ?
        """.trimIndent()

        val paramValues =
            arrayOf(
                user.firstName,
                user.lastName,
                user.email,
                user.password,
                user.role.value,
                user.department,
                user.passwordChangedAt.toInstant().toString(),
                user.passwordResetToken,
                user.passwordResetExpires?.toInstant()?.toString(),
                id
            )

        db.update(sql, *paramValues)

        return findById(id)
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
                rs.getInt("department".dataBaseFieldName()),
                rs.getDateFromString("passwordChangedAt".dataBaseFieldName())!!,
                rs.getStringOrNull("passwordResetToken".dataBaseFieldName()),
                rs.getDateFromString("passwordResetExpires".dataBaseFieldName())
            )
        }

}