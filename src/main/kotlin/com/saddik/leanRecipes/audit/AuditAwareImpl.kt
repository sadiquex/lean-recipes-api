package com.saddik.leanRecipes.audit

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component("auditAwareImpl")
class AuditAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        println(" setting audit")
        return Optional.of("LEAN_RECIPES_MS") // change to user after authentication
    }
}