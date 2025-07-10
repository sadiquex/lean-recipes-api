package com.saddik.leanRecipes.controller.dto.auth

data class LoginResponseDto(
    val token: String,
    val userId: Long,
    val email: String
)
