package com.saddik.leanRecipes.controller.dto.recipe

data class ApiResponseDto<T>(
    val message: String,
    val code: Int,
    val body: T?
) {
}