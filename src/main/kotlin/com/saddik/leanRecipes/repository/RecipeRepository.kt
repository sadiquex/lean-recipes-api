package com.saddik.leanRecipes.repository

import com.saddik.leanRecipes.repository.model.RecipeDBModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor


interface RecipeRepository : JpaRepository<RecipeDBModel, Long>, JpaSpecificationExecutor<RecipeDBModel> {
    fun findRecipeByTitle(title: String): RecipeDBModel?
    fun findAllByTitle(title: String): List<RecipeDBModel>
}



//"Hey Spring, generate the implementation for this repository, and give me all the CRUD and filtering powers I need for working with RecipeDBModel."

//JpaRepository ::	Gives you built-in CRUD methods
//JpaSpecificationExecutor ::	Allows you to build dynamic, flexible queries
//RecipeDBModel ::	Your entity class that maps to the recipes table

//💡 What is JpaRepository<RecipeDBModel, Long>?
//This is the main Spring Data JPA interface.
//
//It gives you CRUD methods for free — no need to write them yourself.
//
//That means:
//
//kotlin
//Copy
//Edit
//findAll()
//findById(id: Long)
//save(entity: RecipeDBModel)
//delete(entity: RecipeDBModel)
//existsById(id: Long)
//…and more.


