package cn.edu.hit.coursety.entity.vo

import cn.edu.hit.coursety.entity.domain.User

data class UserLoginVo(val id: String, val name: String, val gender: String, val major: String,
                       val profession: String, val userType: String) {
    companion object {
        fun fromUser(user: User): UserLoginVo {
            val userType = when (user.userType) {
                0 -> "Admin"
                1 -> "Teacher"
                2 -> "Student"
                else -> "None"
            }
            return UserLoginVo(user.id, user.name, user.gender, user.major, user.profession, userType)
        }
    }
}
