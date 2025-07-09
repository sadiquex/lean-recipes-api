package com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth

// Optional: Response DTO
data class AuthResponse(
    val token: String,
    val name: String,
    val email: String
)
