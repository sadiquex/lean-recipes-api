package com.saddik.leanRecipes.service.impl

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.exceptions.ResourceAlreadyExistsException
import com.saddik.leanRecipes.exceptions.ResourceNotFoundException
import com.saddik.leanRecipes.repository.RecipeRepository
import com.saddik.leanRecipes.repository.dao.IRecipeDao
import com.saddik.leanRecipes.service.IRecipeService
import com.saddik.leanRecipes.utils.log.BaseLog
import com.saddik.leanRecipes.utils.log.LogUtil
import com.saddik.leanRecipes.utils.log.OperationLevel
import org.slf4j.MDC
import org.springframework.stereotype.Service

@Service
class RecipeServiceImpl(val recipeDao: IRecipeDao, val recipeRepository: RecipeRepository) : IRecipeService {
    private val logUtil = LogUtil(OperationLevel.REPOSITORY, this::class.java)
    private val baseLog = BaseLog()

    override fun createRecipe(dto: RecipeDto): RecipeDto? {
        baseLog.message = "Adding recipe service started"
        baseLog.additionalInfo = mutableMapOf("requestId" to MDC.get("requestId"))
        logUtil.log(baseLog)

        return try {
            val recipes = recipeRepository.findAllByTitle(dto.title ?: "")
            if (recipes.isNotEmpty()) {
                throw ResourceAlreadyExistsException("Recipe with title '${dto.title}' already exists")
            }

            val createdRecipe = recipeDao.createRecipe(dto)

            baseLog.message = "Successfully added recipe with id ${createdRecipe?.id}"
            baseLog.additionalInfo?.put("Success", "Recipe created")
            logUtil.log(baseLog)

            createdRecipe

        } catch (ex: ResourceAlreadyExistsException) {
            baseLog.message = ex.message
            logUtil.logE(baseLog, ex)
            throw ex

        } catch (ex: Exception) {
            baseLog.message = "Failed to add recipe: ${ex.message}"
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

    override fun getLatestRecipes(): List<RecipeDto> {
        baseLog.message = "Fetching Latest recipes"
        logUtil.log(baseLog)

        return recipeDao.getLatestRecipes()


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
        return recipeDao.deleteRecipe(id)

    }
}