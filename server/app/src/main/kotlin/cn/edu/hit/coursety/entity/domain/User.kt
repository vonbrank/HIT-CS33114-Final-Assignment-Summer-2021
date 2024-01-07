package cn.edu.hit.coursety.entity.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    var password: String,
    val role: UserRole,
    val department: Int,
    val passwordChangedAt: Date,
    var passwordResetToken: String?,
    var passwordResetExpires: Date?
)

enum class UserRole(val value: String) {
    @JsonProperty("admin")
    ADMIN("admin"),

    @JsonProperty("teacher")
    TEACHER("teacher"),

    @JsonProperty("student")
    STUDENT("student");

    companion object {
        fun roleValueOf(value: String) = values().find { it.value == value }
    }
}
