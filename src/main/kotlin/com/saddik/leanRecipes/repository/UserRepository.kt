package com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository

import com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.model.UserDBModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserDBModel, Long> {
    fun findByEmail(email: String): List<UserDBModel?>
    fun existsByEmail(email: String): Boolean

}
