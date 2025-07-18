package com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.dao

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.RegisterRequest
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.UserDto
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.UserRepository
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.utils.UserMapper
import com.saddik.leanRecipes.controller.dto.auth.LoginRequestDto
import com.saddik.leanRecipes.controller.dto.auth.LoginResponseDto
import org.springframework.stereotype.Service

@Service
class AuthDaoImpl(val userRepository: UserRepository) : IAuthDao {
    override fun createUser(dto: UserDto): UserDto {
        val userDataModel = UserMapper.toEntity(dto)
        val savedUser = userRepository.save(userDataModel)

        return UserMapper.toDto(savedUser)

    }

    override fun loginUser(dto: LoginRequestDto): LoginResponseDto? {
        TODO("Not yet implemented")
    }

}