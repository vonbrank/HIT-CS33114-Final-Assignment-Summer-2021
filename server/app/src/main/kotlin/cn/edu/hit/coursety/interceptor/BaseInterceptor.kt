package cn.edu.hit.coursety.interceptor

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpMethod
import org.springframework.util.AntPathMatcher
import org.springframework.web.servlet.HandlerInterceptor

abstract class BaseInterceptor : HandlerInterceptor {

    private val patterns: MutableMap<HttpMethod, MutableList<String>> = mutableMapOf()
    private val patternsExclude: MutableMap<HttpMethod, MutableList<String>> = mutableMapOf()

    private val antPathMatcher = AntPathMatcher()

    fun match(pattern: String, vararg methods: HttpMethod): BaseInterceptor {
        patterns.insert(pattern, *methods)
        return this
    }

    fun matchExclude(pattern: String, vararg methods: HttpMethod): BaseInterceptor {
        patternsExclude.insert(pattern, *methods)
        return this
    }

    private fun MutableMap<HttpMethod, MutableList<String>>.insert(pattern: String, vararg methods: HttpMethod) {
        if (methods.isNotEmpty()) {
            methods.toList()
        } else {
            HttpMethod.values().filter { it != HttpMethod.OPTIONS }
        }.forEach { method ->
            var list = this[method]
            if (list == null) {
                list = mutableListOf()
                this[method] = list
            }
            list.add(pattern)
        }
    }


    private fun List<String>.antPatternMatch(path: String): Boolean {
        return this.find { antPathMatcher.match(it, path) } != null
    }

    protected fun HttpServletRequest.matchPatterns(): Boolean {

        val path = this.requestURI
        val method = HttpMethod.valueOf(this.method)
        val list = patterns[method]
        val listExclude = patternsExclude[method]

        return (list != null && list.antPatternMatch(path)) && !(listExclude != null && listExclude.antPatternMatch(path))

    }
}