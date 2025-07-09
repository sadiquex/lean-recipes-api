package com.saddik.leanRecipes.repository.dao

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.recipe.RecipeDto

interface IRecipeDao {
    fun createRecipe(dto: RecipeDto): RecipeDto?

    fun getAllRecipes(): List<RecipeDto>

    fun getRecipeById(id: Long): RecipeDto?

    fun getLatestRecipes(): List<RecipeDto>

//    fun updateRecipe(id: Long, request: RecipeDto): RecipeResponse

    fun deleteRecipe(id: Long): Boolean

}