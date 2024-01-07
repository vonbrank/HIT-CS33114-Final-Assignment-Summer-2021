package cn.edu.hit.coursety.interceptor

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpMethod
import org.springframework.util.AntPathMatcher
import org.springframework.web.servlet.HandlerInterceptor

abstract class BaseInterceptor : HandlerInterceptor {

    private val patterns: MutableMap<HttpMethod, MutableList<String>> = mutableMapOf()

    private val antPathMatcher = AntPathMatcher()

    fun match(pattern: String, vararg methods: HttpMethod): BaseInterceptor {

        if (methods.isNotEmpty()) {
            methods.toList()
        } else {
            HttpMethod.values().filter { it != HttpMethod.OPTIONS }
        }.forEach { method ->
            var list = patterns[method]
            if (list == null) {
                list = mutableListOf()
                patterns[method] = list
            }
            list.add(pattern)
        }
        return this
    }

    private fun List<String>.antPatternMatch(path: String): Boolean {
        return this.find { antPathMatcher.match(it, path) } != null
    }

    protected fun HttpServletRequest.matchPatterns(): Boolean {

        val path = this.requestURI
        val method = HttpMethod.valueOf(this.method)
        val list = patterns[method]

        return list != null && list.antPatternMatch(path)

    }
}