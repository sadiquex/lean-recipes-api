package com.saddik.leanRecipes.service

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import org.springframework.data.domain.Page

interface IRecipeService {
    fun createRecipe(dto: RecipeDto): RecipeDto?

    fun getRecipeById(id: Long): RecipeDto?

    fun getAllRecipes(): List<RecipeDto>

    fun getLatestRecipes(): List<RecipeDto>

//    fun updateRecipe(id: Long, request: RecipeDto): RecipeResponse

    fun deleteRecipe(id: Long): Boolean
}
