package com.saddik.leanRecipes.config.com.saddik.leanRecipes.controller.dto

data class ApiResponseDto<T>(
    val message: String,
    val code: Int,
    val body: T?
) {
}