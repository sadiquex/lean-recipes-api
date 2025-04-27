package com.saddik.leanRecipes.controller

import com.saddik.leanRecipes.controller.dto.recipe.ApiResponseDto
import com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.service.IRecipeService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/recipes")
class RecipeController(val recipeService: IRecipeService) {
//    TODO: setup logging
val logUtil = LogUtil(
    OperationLevel.CONTROLLER,
    this::class.java
)
    val baseLog = BaseLog()


    @PostMapping
    fun createRecipe(@Valid @RequestBody payload: RecipeDto): ResponseEntity<ApiResponseDto<RecipeDto>> {


        val addRecipeResponse = recipeService.createRecipe(payload)

//        baseLog.message = "Recipe added with response $addRecipeResponse"
//        logUtil.log(baseLog)

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
    fun getAllRecipes( ): ResponseEntity<ApiResponseDto<List<RecipeDto>>> {

        val allRecipes = recipeService.getAllRecipes()

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

}