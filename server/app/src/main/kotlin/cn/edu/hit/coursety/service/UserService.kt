package cn.edu.hit.coursety.service

import cn.edu.hit.coursety.dao.UserDao
import cn.edu.hit.coursety.entity.domain.User
import cn.edu.hit.coursety.entity.dto.SignupDto
import org.springframework.stereotype.Service

@Service
class UserService(val userDao: UserDao, val authService: AuthService) {

    fun getAllUser(): List<User> {
        return userDao.findAll()
    }

    fun getUserById(id: Int): User {
        return userDao.findById(id)
    }
}