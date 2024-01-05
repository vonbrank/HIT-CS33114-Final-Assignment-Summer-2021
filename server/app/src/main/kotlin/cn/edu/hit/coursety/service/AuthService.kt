package cn.edu.hit.coursety.service

import at.favre.lib.crypto.bcrypt.BCrypt
import cn.edu.hit.coursety.config.JwtConfig
import cn.edu.hit.coursety.dao.UserDao
import cn.edu.hit.coursety.entity.domain.User
import cn.edu.hit.coursety.entity.dto.LoginDto
import cn.edu.hit.coursety.entity.dto.SignupDto
import cn.edu.hit.coursety.entity.vo.LoginVo
import cn.edu.hit.coursety.entity.vo.UserVo
import cn.edu.hit.coursety.exception.AppException
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.Calendar

@Service
class AuthService(val userDao: UserDao, val jwtConfig: JwtConfig) {

    fun signup(signupDto: SignupDto): UserVo {
        signupDto.password = bcryptPassword((signupDto.password))
        val id = userDao.create(signupDto)
        return UserVo(userDao.findById(id))
    }

    fun login(loginDto: LoginDto): LoginVo {
        val user = userDao.findByEmail(loginDto.email)
        if (!verifyPassword(loginDto.password, user.password)) {
            throw AppException("Incorrect email or password", HttpStatus.UNAUTHORIZED)
        }
        val token = signToken(user.id)
        return LoginVo(token, UserVo(user))
    }

    fun authorize(authorization: String) {
        val authorizationSplit = authorization.split(" ")
        if (authorization.length >= 2) {
            val (_, token) = authorizationSplit
            verifyToken(token)
        } else {
            throw AppException("You are not logged in! Please log in to get access.", HttpStatus.UNAUTHORIZED)
        }
    }

    fun bcryptPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }

    fun verifyPassword(candidatePassword: String, userPassword: String): Boolean {
        return BCrypt.verifyer().verify(candidatePassword.toCharArray(), userPassword).verified
    }

    fun signToken(id: Int): String {

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, jwtConfig.expiredInDays.toInt())
        val expiredDate = calendar.time

        val algorithm = Algorithm.HMAC256(jwtConfig.secret)
        return JWT.create().withClaim("id", id).withExpiresAt(expiredDate).sign(algorithm)
    }

    fun verifyToken(candidateToken: String) {
        runCatching {
            val algorithm = Algorithm.HMAC256(jwtConfig.secret)
            val verifier = JWT.require(algorithm).build()
            verifier.verify(candidateToken)
        }.onFailure {
            if (it is JWTVerificationException) {
                throw AppException("You are not logged in! Please log in to get access.", HttpStatus.UNAUTHORIZED)
            }
        }


    }
}