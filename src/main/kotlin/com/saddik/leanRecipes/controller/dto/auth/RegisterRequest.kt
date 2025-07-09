package com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth

// src/main/kotlin/com/saddik/leanRecipes/controller/dto/auth/RegisterRequest.kt
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
