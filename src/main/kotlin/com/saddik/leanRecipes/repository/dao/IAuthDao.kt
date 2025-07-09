package com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.dao

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.LoginRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.RegisterRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.UserDto

interface IAuthDao {
    fun createUser(dto: UserDto): UserDto

    fun loginUser(dto: LoginRequest): LoginRequest?

}