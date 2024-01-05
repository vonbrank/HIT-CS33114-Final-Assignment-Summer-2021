package cn.edu.hit.coursety.entity.vo

import cn.edu.hit.coursety.entity.domain.User
import cn.edu.hit.coursety.entity.domain.UserRole

data class UserVo(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: UserRole,
    val department: Int
) {
    constructor(user: User) : this(user.id, user.firstName, user.lastName, user.email, user.role, user.department) {

    }
}
