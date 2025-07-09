package com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth

data class UserDto(
    val id: Long? = null,

    val email: String = "",

    val password: String = "",

    val createdAt: String?,
    val updatedAt: String?

)