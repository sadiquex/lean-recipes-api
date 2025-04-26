package com.saddik.leanRecipes.repository.dao

import com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.repository.RecipeRepository
import com.saddik.leanRecipes.utils.RecipeMapper
import org.slf4j.MDC
import org.springframework.stereotype.Service

@Service
class RecipeDaoImpl(val recipeRepository: RecipeRepository) : IRecipeDao {
    override fun createRecipe(dto: RecipeDto): RecipeDto? {
        try {
            val incomeDataModel = RecipeMapper.toEntity(dto)
//            save to db
            recipeRepository.save(incomeDataModel)

            return dto
        } catch (e: Exception) {

            throw e

        }
    }

    override fun getRecipeById(id: Long): RecipeDto? {
        TODO("Not yet implemented")
    }

    override fun getAllRecipes(): List<RecipeDto> {
        TODO("Not yet implemented")
    }

    override fun deleteRecipe(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}