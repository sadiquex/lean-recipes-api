package com.saddik.leanRecipes.service.impl

import com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.repository.dao.IRecipeDao
import com.saddik.leanRecipes.service.IRecipeService
import org.springframework.stereotype.Service

@Service
class RecipeServiceImpl(val recipeDao: IRecipeDao) : IRecipeService {
    override fun createRecipe(dto: RecipeDto): RecipeDto? {
        try {
            return recipeDao.createRecipe(dto)
        } catch (ex: Exception) {
//            logger.error("Failed to create recipe: ${ex.message}", ex)
            throw RuntimeException("Unable to create recipe at this time. Please try again later.")
        }

    }

    override fun getAllRecipes(): List<RecipeDto> {
        try {
            return recipeDao.getAllRecipes()
        } catch (ex: Exception) {
//            return emptyList()
//            baseLog.message = e.message
//            logUtil.logE(baseLog, e)
            throw RuntimeException("Unable to get all recipes at this time. Please try again later.")
        }

    }

    override fun getRecipeById(id: Long): RecipeDto? {
        TODO("Not yet implemented")
    }

    override fun deleteRecipe(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}