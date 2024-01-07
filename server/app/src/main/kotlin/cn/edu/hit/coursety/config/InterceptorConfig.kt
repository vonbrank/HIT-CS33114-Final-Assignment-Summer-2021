package cn.edu.hit.coursety.config

import cn.edu.hit.coursety.entity.domain.UserRole
import cn.edu.hit.coursety.interceptor.ProtectInterceptor
import cn.edu.hit.coursety.interceptor.RestrictToInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig(
    val protectInterceptor: ProtectInterceptor,
    val restrictToInterceptor: RestrictToInterceptor
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(
            protectInterceptor
                .match("/api/v1/users/**")
                .matchExclude("/api/v1/users/signup")
                .matchExclude("/api/v1/users/login")
                .matchExclude("/api/v1/users/forgotPassword")
                .matchExclude("/api/v1/users/resetPassword/**")
        )
            .addPathPatterns("/**")

        registry.addInterceptor(
            restrictToInterceptor
                .setRoles(UserRole.ADMIN)
                .match("/api/v1/users")
        )
            .addPathPatterns("/**")
    }
}