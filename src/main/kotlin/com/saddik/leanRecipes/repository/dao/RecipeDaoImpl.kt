package com.saddik.leanRecipes.repository.dao

import com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.exceptions.ResourceNotFoundException
import com.saddik.leanRecipes.repository.RecipeRepository
import com.saddik.leanRecipes.utils.RecipeMapper
import com.saddik.leanRecipes.utils.log.BaseLog
import com.saddik.leanRecipes.utils.log.LogUtil
import com.saddik.leanRecipes.utils.log.OperationLevel
import jakarta.transaction.Transactional
import org.slf4j.MDC
import org.springframework.stereotype.Service


@Service
class RecipeDaoImpl(val recipeRepository: RecipeRepository) : IRecipeDao {
    private val logUtil = LogUtil(OperationLevel.REPOSITORY, this::class.java)
    private val baseLog = BaseLog()

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
        return allRecipes.map { RecipeMapper.toDto(it) } // convert to recipe and return
    }

    override fun getRecipeById(id: Long): RecipeDto? {
        val recipe = recipeRepository.findById(id)
        val searchResult = recipeRepository.findById(id)

        if (searchResult.isPresent) {
            val singleRecipeData = searchResult.get()

//            baseLog.additionalInfo?.put(
//                "success", "Successfully retrieved details of expense with id: $id :: singleRecipeData"
//            )
//            logUtil.log(baseLog)

            val returnDto = RecipeMapper.toDto(singleRecipeData)

            return returnDto
        } else {

//            baseLog.additionalInfo?.put("failure", "Expense with id: $id :: not found")
//            logUtil.log(baseLog)

            throw ResourceNotFoundException(
                message = "unable to find recipe with id $id",
            )
        }

    }

    override fun getLatestRecipes(): List<RecipeDto> {
        baseLog.message = "Fetching latest 8 recipes"
        logUtil.log(baseLog)

        val latestRecipes = recipeRepository.findTop8ByOrderByCreatedAtDesc()

        return latestRecipes.map { RecipeMapper.toDto(it) }
    }

//    override fun getAllRecipes(page: Int, size: Int, sortBy: String, direction: String): Page<RecipeDto> {
//
//
//    }

    override fun deleteRecipe(id: Long): Boolean {
        try {
            val requestId = MDC.get("requestId")
            baseLog.message = "Delete recipe with id '$id' dao impl started'"
            baseLog.additionalInfo = mutableMapOf("requestId" to requestId)
            logUtil.log(baseLog)

            val recipe = recipeRepository.findById(id)
            if (recipe.isPresent) {
                val recipeEntity = recipe.get()
                recipeRepository.delete(recipeEntity)
                baseLog.message = "Successfully deleted recipe with id '$id'"
                logUtil.log(baseLog)

                return true

            } else {
                throw ResourceNotFoundException(message = "Recipe with id '$id' not found")
            }

        } catch (ex: Exception) {
            baseLog.message = ex.message
            logUtil.logE(baseLog, ex)
            throw ex
        }

    }
}