package com.saddik.leanRecipes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
class LeanRecipesApplication

fun main(args: Array<String>) {
	runApplication<LeanRecipesApplication>(*args)
}
