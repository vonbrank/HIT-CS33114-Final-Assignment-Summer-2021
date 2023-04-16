package cn.edu.hit.coursety.service

import cn.edu.hit.coursety.dao.UserDao
import cn.edu.hit.coursety.entity.domain.User
import org.springframework.stereotype.Service

@Service
class UserService(val userDao: UserDao) {
    fun checkUserAuthorization(id: String, password: String): Boolean {
        return userDao.getUserByIdAndPassword(id, password) != null
    }

    fun getUserById(id: String): User? {
        return userDao.getUserById(id)
    }
}