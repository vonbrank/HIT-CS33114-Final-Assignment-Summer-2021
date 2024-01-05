package cn.edu.hit.coursety.service

import cn.edu.hit.coursety.dao.UserDao
import cn.edu.hit.coursety.entity.domain.User
import org.springframework.stereotype.Service

@Service
class UserService(val userDao: UserDao) {

    fun getAllUser(): List<User> {
        return userDao.findAll()
    }

    fun getUserById(id: Int): User? {
        return userDao.findById(id)
    }
}