package cn.edu.hit.coursety.service

import at.favre.lib.crypto.bcrypt.BCrypt
import cn.edu.hit.coursety.config.JwtConfig
import cn.edu.hit.coursety.dao.UserDao
import cn.edu.hit.coursety.entity.dto.LoginDto
import cn.edu.hit.coursety.entity.dto.ResetPasswordDto
import cn.edu.hit.coursety.entity.dto.SignupDto
import cn.edu.hit.coursety.entity.dto.UpdatePasswordDto
import cn.edu.hit.coursety.entity.vo.LoginVo
import cn.edu.hit.coursety.entity.vo.UserVo
import cn.edu.hit.coursety.exception.AppException
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

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

    fun bcryptPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }

    fun verifyPassword(candidatePassword: String, userPassword: String): Boolean {
        return BCrypt.verifyer().verify(candidatePassword.toCharArray(), userPassword).verified
    }

    fun signToken(id: Int): String {
        var currentZoneTime = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC)
        currentZoneTime = currentZoneTime.plusDays(jwtConfig.expiredInDays.toLong())
//        currentZoneTime = currentZoneTime.plusSeconds(3)
        val expiredDate = currentZoneTime.toInstant()

        val algorithm = Algorithm.HMAC256(jwtConfig.secret)
        return JWT.create().withClaim("id", id).withExpiresAt(expiredDate).withIssuedAt(Instant.now()).sign(algorithm)
    }

    fun verifyToken(candidateToken: String): DecodedJWT? {
        var decodedJWT: DecodedJWT? = null
        runCatching {
            val algorithm = Algorithm.HMAC256(jwtConfig.secret)
            val verifier = JWT.require(algorithm).build()
            decodedJWT = verifier.verify(candidateToken)
        }.onFailure {
            throw AppException(
                "Token is invalid or has expired.",
                HttpStatus.UNAUTHORIZED
            )
        }

        return decodedJWT
    }

    fun createPasswordResetToken(email: String): String {
        val user = userDao.findByEmail(email)

        val stringBuilder = StringBuilder()
        val random = Random()
        repeat(32) {
            val randomValue = random.nextInt(16)
            stringBuilder.append(Integer.toHexString(randomValue))
        }
        val resetToken = stringBuilder.toString()

        var currentZoneTime = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC)
        currentZoneTime = currentZoneTime.plusDays(jwtConfig.expiredInDays.toLong())
        val expiredDate = currentZoneTime.toInstant()

        user.passwordResetToken = resetToken
        user.passwordResetExpires = Date.from(expiredDate)

        userDao.save(user)

        return resetToken
    }

    fun resetPassword(resetToken: String, resetPasswordDto: ResetPasswordDto): LoginVo {
        val user = userDao.findByResetToken(resetToken)

        user.password = bcryptPassword((resetPasswordDto.password))
        user.passwordResetToken = null
        user.passwordResetExpires = null
        userDao.save(user)

        val token = signToken(user.id)
        return LoginVo(token, UserVo(user))
    }

    fun updatePassword(id: Int, updatePasswordDto: UpdatePasswordDto): UserVo {
        val user = userDao.findById(id)

        if (!verifyPassword(updatePasswordDto.currentPassword, user.password)) {
            throw AppException("Your current password is wrong!", HttpStatus.UNAUTHORIZED)
        }

        user.password = bcryptPassword((updatePasswordDto.newPassword))
        user.passwordChangedAt = Date.from(Instant.now())
        return UserVo(userDao.save(user))
    }
}