package com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.model

import com.saddik.leanRecipes.repository.model.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserDBModel:BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = ""

    @Column(unique = true)
    var email: String = ""

    var password: String = ""

//    @Column(name = "created_at")
//    var createdAt: LocalDateTime = LocalDateTime.now()

//    @Column(name = "updated_at")
//    var updatedAt: LocalDateTime = LocalDateTime.now()
}
