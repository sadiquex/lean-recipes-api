package com.saddik.leanRecipes.controller.dto.recipe

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RecipeDto(
    val id: Long? = null,

    @field:NotBlank(message = "Title must not be blank")
    @field:Size(max = 100, message = "Title must be under 100 characters")
    val title: String?,

    @field:NotBlank(message = "Description must not be blank")
    val description: String,

    @field:NotBlank(message = "Image must not be blank")
    val photoUrl: String
)
