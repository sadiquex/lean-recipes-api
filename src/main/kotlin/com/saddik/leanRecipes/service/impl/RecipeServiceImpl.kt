package com.saddik.leanRecipes.service.impl

import com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.repository.dao.IRecipeDao
import com.saddik.leanRecipes.service.IRecipeService
import org.springframework.stereotype.Service

@Service
class RecipeServiceImpl(val recipeDao: IRecipeDao) : IRecipeService {
    override fun createRecipe(dto: RecipeDto): RecipeDto? {
        return recipeDao.createRecipe(dto)
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