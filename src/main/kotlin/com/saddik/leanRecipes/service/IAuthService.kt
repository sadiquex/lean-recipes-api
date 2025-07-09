package com.saddik.leanRecipes.config.com.saddik.leanRecipes.service

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.LoginRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.RegisterRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.UserDto

interface IAuthService {
    fun createUser(dto: UserDto): UserDto?

    fun loginUser(dto: LoginRequest): LoginRequest?

}