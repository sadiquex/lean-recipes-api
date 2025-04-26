package com.saddik.leanRecipes.utils

//import com.saddik.leanRecipes.dto.CreateRecipeRequest
//import com.saddik.leanRecipes.dto.RecipeResponse
import com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.repository.model.RecipeDBModel

object RecipeMapper {

    fun toEntity(dto: RecipeDto, model: RecipeDBModel? = null): RecipeDBModel {
        val entity = model ?: RecipeDBModel() // if model is null, create a new one

        entity.id = dto.id
        entity.title = dto.title
        entity.description = dto.description
        entity.photoUrl = dto.photoUrl

        return entity
    }

    fun toDto(model: RecipeDBModel): RecipeDto {
        return RecipeDto(
            id = model.id,
            title = model.title,
            description = model.description,
            photoUrl = model.photoUrl
        )
    }
}
