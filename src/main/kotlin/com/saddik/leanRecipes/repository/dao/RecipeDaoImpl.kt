package com.saddik.leanRecipes.repository.dao

import com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.exceptions.ResourceNotFoundException
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
        val recipe = recipeRepository.findById(id)
        val searchResult = recipeRepository.findById(id)

//        if (recipe.isPresent) {
//            val recipeEntity = recipe.get()
//
//            //                convert into dto for client
//            val convertedRecipe = RecipeMapper.toDto(recipeEntity)
//            return convertedRecipe
//        } else {
//            throw ResourceNotFoundException(message = "Recipe with id $id not found")
//        }

        if (searchResult.isPresent) {
            val singleExpenseData = searchResult.get()

//            baseLog.additionalInfo?.put(
//                "success", "Successfully retrieved details of expense with id: $id :: singleExpenseData"
//            )
//            logUtil.log(baseLog)

            val returnDto = RecipeMapper.toDto(singleExpenseData)

            return returnDto
        } else {

//            baseLog.additionalInfo?.put("failure", "Expense with id: $id :: not found")
//            logUtil.log(baseLog)

            throw ResourceNotFoundException(
                message = "unable to find recipe with id $id",
            )
        }

    }

//    override fun getAllRecipes(page: Int, size: Int, sortBy: String, direction: String): Page<RecipeDto> {
//
//
//    }

    override fun deleteRecipe(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}