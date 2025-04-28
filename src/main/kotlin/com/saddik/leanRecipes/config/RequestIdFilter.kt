package com.saddik.leanRecipes.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID

@Component
class RequestInterceptorFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestId = UUID.randomUUID().toString()
        MDC.put("requestId", requestId)
        try {
            filterChain.doFilter(request, response)
        } finally {
            MDC.clear()  // ensures MDC is cleared after each request to prevent leaking request IDs into subsequent requests in multi-threaded environments.
        }
    }
}