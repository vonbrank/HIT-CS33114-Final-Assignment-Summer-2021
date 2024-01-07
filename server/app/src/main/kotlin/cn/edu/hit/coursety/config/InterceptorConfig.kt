package cn.edu.hit.coursety.config

import cn.edu.hit.coursety.interceptor.ProtectInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig(
    val protectInterceptor: ProtectInterceptor,
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(
            protectInterceptor
                .match("/api/v1/users/**")
        )
            .addPathPatterns("/**")
    }
}