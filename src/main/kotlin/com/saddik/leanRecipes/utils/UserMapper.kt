package com.saddik.leanRecipes.config.com.saddik.leanRecipes.utils

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.auth.UserDto
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.model.UserDBModel

object UserMapper {
    fun toEntity(dto: UserDto, model: UserDBModel? = null): UserDBModel {
        val entity = model ?: UserDBModel()

        entity.id = dto.id
        entity.email = dto.email
        entity.password = dto.password


        return entity

    }

    fun toDto(model: UserDBModel): UserDto {
        return UserDto(
            id = model.id,
            email = model.email,
            password = model.password,
            createdAt = model.createdAt?.toString(),
            updatedAt = model.updatedAt?.toString(),
        )
    }

}