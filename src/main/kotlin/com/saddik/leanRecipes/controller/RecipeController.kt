package com.saddik.leanRecipes.controller

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.ApiResponseDto
import com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.service.IRecipeService
import com.saddik.leanRecipes.utils.log.BaseLog
import com.saddik.leanRecipes.utils.log.LogUtil
import com.saddik.leanRecipes.utils.log.OperationLevel
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/recipes")
@CrossOrigin(origins = ["http://localhost:5173"])
class RecipeController(val recipeService: IRecipeService) {
    val logUtil = LogUtil(
        OperationLevel.CONTROLLER, RecipeController::class.java
    )

    val baseLog = BaseLog()

    @PostMapping
    fun createRecipe(@Valid @RequestBody payload: RecipeDto): ResponseEntity<ApiResponseDto<RecipeDto>> {
        val addRecipeResponse = recipeService.createRecipe(payload)

        baseLog.message = "Recipe added with response $addRecipeResponse"
        logUtil.log(baseLog)

        val code = if (addRecipeResponse != null) 201 else 400
        val message = if (addRecipeResponse != null) "Recipe created successfully" else "Failed to create recipe"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = addRecipeResponse
        )
        return ResponseEntity.status(code).body(apiResponse)

    }

    @GetMapping
    fun getAllRecipes(): ResponseEntity<ApiResponseDto<List<RecipeDto>>> {
        baseLog.message = "Get all recipes controller initiated"
        logUtil.log(baseLog)

        val allRecipes = recipeService.getAllRecipes()

        baseLog.message = "All recipe data retrieved with response: $allRecipes"
        logUtil.log(baseLog)

        val isSuccess = allRecipes.isNotEmpty()
        val code = if (isSuccess) 200 else 400
        val message = if (isSuccess) "Recipes fetched successfully" else "Failed to fetch recipes"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = allRecipes
        )
        return ResponseEntity.status(code).body(apiResponse)

    }

    @GetMapping("/{id}")
    fun getRecipeById(@PathVariable("id") id: Long): ResponseEntity<ApiResponseDto<RecipeDto>> {
        baseLog.message = "Get recipe by Id controller initiated"
        logUtil.log(baseLog)

        val singleRecipe = recipeService.getRecipeById(id)

        baseLog.message = "Single recipe data retrieved with response : $singleRecipe"
        logUtil.log(baseLog)

        val code = 200
        val message = "Recipe with id '$id' retrieved"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = singleRecipe
        )
        return ResponseEntity.status(code).body(apiResponse)
    }

    @DeleteMapping("/{id}")
    fun deleteRecipe(@PathVariable("id") id: Long): ResponseEntity<ApiResponseDto<Boolean>> {
        baseLog.message = "Delete recipe controller initiated"
        logUtil.log(baseLog)

        val singleRecipe = recipeService.deleteRecipe(id)

        baseLog.message = "Recipe with id '$id' deleted: $singleRecipe"
        logUtil.log(baseLog)

        val code = 200
        val message = "Recipe deleted"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = singleRecipe
        )

        return ResponseEntity.status(code).body(apiResponse)


    }

    @GetMapping("/latest")
    fun getLatestRecipes(): ResponseEntity<ApiResponseDto<List<RecipeDto>>> {
        baseLog.message = "Get latest recipes controller initiated"
        logUtil.log(baseLog)

        val latestRecipes = recipeService.getLatestRecipes()
        val isSuccess = latestRecipes.isNotEmpty()
        val code = if (isSuccess) 200 else 400

        val apiResponse = ApiResponseDto(
            message = "Latest recipes fetched",
            code = code,
            body = latestRecipes
        )
        return ResponseEntity.status(code).body(apiResponse)

    }

}