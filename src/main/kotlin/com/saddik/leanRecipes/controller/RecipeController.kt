package com.saddik.leanRecipes.controller

import com.saddik.leanRecipes.controller.dto.recipe.ApiResponseDto
import com.saddik.leanRecipes.controller.dto.recipe.RecipeDto
import com.saddik.leanRecipes.service.IRecipeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("/api/v1/recipes")
class RecipeController(val recipeService: IRecipeService) {
//    TODO: setup logging

    @PostMapping
    fun createRecipe(@Valid @RequestBody payload: RecipeDto): ResponseEntity<ApiResponseDto<RecipeDto>> {


        val addRecipeResponse = recipeService.createRecipe(payload)

//        baseLog.message = "Income added with response $addRecipeResponse"
//        logUtil.log(baseLog)

        val code = if (addRecipeResponse != null) 201 else 400
        val message = if (addRecipeResponse != null) "Income created successfully" else "Failed to create income"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = addRecipeResponse
        )
        return ResponseEntity.status(code).body(apiResponse)

    }

}