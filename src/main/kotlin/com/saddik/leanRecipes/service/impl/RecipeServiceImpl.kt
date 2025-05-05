package com.saddik.leanRecipes.service.impl

import com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.exceptions.ResourceNotFoundException
import com.saddik.leanRecipes.repository.dao.IRecipeDao
import com.saddik.leanRecipes.service.IRecipeService
import com.saddik.leanRecipes.utils.log.BaseLog
import com.saddik.leanRecipes.utils.log.LogUtil
import com.saddik.leanRecipes.utils.log.OperationLevel
import org.slf4j.MDC
import org.springframework.stereotype.Service

@Service
class RecipeServiceImpl(val recipeDao: IRecipeDao) : IRecipeService {
    private val logUtil = LogUtil(OperationLevel.REPOSITORY, this::class.java)
    private val baseLog = BaseLog()

    override fun createRecipe(dto: RecipeDto): RecipeDto? {
        try {
//            log what's happening
            //            1. get the requestId (but we're now setting it in LogUtil)
//            2. set the message and additional info using the requestId
            baseLog.message = "Adding recipe service started"
//            3. pass this info to logUtil.log method
            baseLog.additionalInfo?.put("Success", "Successfully added recipe with id ${dto.id}")

            logUtil.log(baseLog)

            return recipeDao.createRecipe(dto)
        } catch (ex: Exception) {
            baseLog.message = "Failed to add recipe: ${ex.message}"
            baseLog.additionalInfo = mutableMapOf(
                "requestId" to MDC.get("requestId")
            )
            logUtil.logE(baseLog, ex)

            throw RuntimeException("Unable to create recipe at this time. Please try again later.")
        }

    }

    override fun getAllRecipes(): List<RecipeDto> {
        try {
            baseLog.message = "Fetching all recipe service started"
            logUtil.log(baseLog)

            return recipeDao.getAllRecipes()

        } catch (ex: Exception) {
//            return emptyList()
            baseLog.message = ex.message
            logUtil.logE(baseLog, ex)
            throw RuntimeException("Unable to get all recipes at this time. Please try again later.")
        }

    }

    override fun getRecipeById(id: Long): RecipeDto? {
        try {
            baseLog.message = "Fetching single recipe service started"
            logUtil.log(baseLog)

//            return recipeDao.getRecipeById(id)

            val recipe = recipeDao.getRecipeById(id)
            if (recipe == null) {
                throw ResourceNotFoundException("Recipe with id $id not found")
            }
//
            return recipe


        } catch (ex: Exception) {
            baseLog.message = ex.message
            logUtil.logE(baseLog, ex)
            throw RuntimeException("Unable to get recipe with id $id")
        }

    }

    override fun deleteRecipe(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}