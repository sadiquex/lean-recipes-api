package com.saddik.leanRecipes.controller.dto.recipe

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.Instant


data class RecipeDto(
    val id: Long? = null,

    @field:NotBlank(message = "Title must not be blank")
    @field:Size(max = 100, message = "Title must be under 100 characters")
    val title: String?,

    @field:NotBlank(message = "Description must not be blank")
    val description: String,

    @field:NotBlank(message = "Photo URL must not be blank")
    val photoUrl: String,

//    @field:NotNull(message = "Ingredients list must not be null")
//    val ingredients: List<@NotBlank(message = "Ingredient must not be blank") String>,
//
//    @field:NotNull(message = "Directions list must not be null")
//    val directions: List<@NotBlank(message = "Direction step must not be blank") String>,

    val ingredients: List<@NotBlank(message = "Ingredient must not be blank") String> = emptyList(),

    val directions: List<@NotBlank(message = "Direction must not be blank") String> = emptyList(),

    @field:NotNull(message = "Servings must be specified")
    val servings: Int,

    val yield: String? = null, // optional

    @field:NotNull(message = "Prep time must be specified")
    val prepTimeMinutes: Int,

    val cookTimeMinutes: Int? = null, // optional

//    val createdAt: Instant? = null, // 👈 add this
//    val updatedAt: Instant? = null, // (optional, add updatedAt if you want)

    val createdAt: String?,  // <-- STRING TYPE
    val updatedAt: String?


)
