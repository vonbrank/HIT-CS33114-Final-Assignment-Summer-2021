package cn.edu.hit.coursety.config


import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
class JwtConfig(
    @Value("\${jwt.secret}") val secret: String,
    @Value("\${jwt.expired-in-days}") val expiredInDays: String
) {
}
