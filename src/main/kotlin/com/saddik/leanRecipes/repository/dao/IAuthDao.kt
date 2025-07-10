package com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.dao

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.RegisterRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.UserDto
import com.saddik.leanRecipes.controller.dto.auth.LoginRequestDto
import com.saddik.leanRecipes.controller.dto.auth.LoginResponseDto

interface IAuthDao {
    fun createUser(dto: UserDto): UserDto

    fun loginUser(dto: LoginRequestDto): LoginResponseDto?

}