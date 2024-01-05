package cn.edu.hit.coursety.entity.domain

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: UserRole,
    val department: Int
)

enum class UserRole(val value: String) {
    ADMIN("admin"),
    TEACHER("teacher"),
    STUDENT("student")

}
