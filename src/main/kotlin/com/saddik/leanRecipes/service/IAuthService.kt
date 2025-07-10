package com.saddik.leanRecipes.config.com.saddik.leanRecipes.service

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.RegisterRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.UserDto
import com.saddik.leanRecipes.controller.dto.auth.LoginRequestDto
import com.saddik.leanRecipes.controller.dto.auth.LoginResponseDto

interface IAuthService {
    fun createUser(dto: UserDto): UserDto?

    //    TODO: remove any after
//    fun loginUser(dto: LoginRequestDto): LoginResponseDto?
    fun loginUser(dto: LoginRequestDto): Any

}