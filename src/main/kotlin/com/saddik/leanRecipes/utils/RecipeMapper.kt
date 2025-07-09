package com.saddik.leanRecipes.utils

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.repository.model.RecipeDBModel

object RecipeMapper {

    fun toEntity(dto: RecipeDto, model: RecipeDBModel? = null): RecipeDBModel {
        val entity = model ?: RecipeDBModel()

        entity.id = dto.id
        entity.title = dto.title
        entity.description = dto.description
        entity.photoUrl = dto.photoUrl
        entity.ingredients = dto.ingredients
        entity.directions = dto.directions
        entity.servings = dto.servings
        entity.yield = dto.yield
        entity.prepTimeMinutes = dto.prepTimeMinutes
        entity.cookTimeMinutes = dto.cookTimeMinutes

        return entity
    }

    fun toDto(model: RecipeDBModel): RecipeDto {
        return RecipeDto(
            id = model.id,
            title = model.title,
            description = model.description,
            photoUrl = model.photoUrl,
            ingredients = model.ingredients,
            directions = model.directions,
            servings = model.servings,
            yield = model.yield,
            prepTimeMinutes = model.prepTimeMinutes,
            cookTimeMinutes = model.cookTimeMinutes,
            createdAt = model.createdAt?.toString(),
            updatedAt = model.updatedAt?.toString()

        )
    }
}
