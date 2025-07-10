package com.saddik.leanRecipes.controller.dto.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginRequestDto(
    @field:Email(message = "Email must be valid")
    val email: String,

    @field:NotBlank(message = "Password must not be blank")
    val password: String
)
