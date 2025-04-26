package com.saddik.leanRecipes.repository.dao

import com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.repository.RecipeRepository
import com.saddik.leanRecipes.utils.RecipeMapper
import jakarta.transaction.Transactional
import org.slf4j.MDC
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@Service
class RecipeDaoImpl(val recipeRepository: RecipeRepository) : IRecipeDao {
//    DAO focuses only on database transactions

    @Transactional
    override fun createRecipe(dto: RecipeDto): RecipeDto? {
        val recipeDataModel = RecipeMapper.toEntity(dto)
        val savedRecipe = recipeRepository.save(recipeDataModel)
        return RecipeMapper.toDto(savedRecipe)
    }

    override fun getAllRecipes(): List<RecipeDto> {
        val allRecipes = recipeRepository.findAll()
//            baseLog.additionalInfo?.put("Success", "Successfully fetched ${allRecipes.size}")
        return allRecipes.map { RecipeMapper.toDto(it) } // convert to income and return
    }

    override fun getRecipeById(id: Long): RecipeDto? {
        TODO("Not yet implemented")
    }

//    override fun getAllRecipes(page: Int, size: Int, sortBy: String, direction: String): Page<RecipeDto> {
//
//
//    }

    override fun deleteRecipe(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}