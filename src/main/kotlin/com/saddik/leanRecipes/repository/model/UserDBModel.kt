package com.saddik.leanRecipes.config.com.saddik.leanRecipes.repository.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.saddik.leanRecipes.repository.model.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserDBModel : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = ""

    @Column(unique = true)
    var email: String = ""

    @Column
    var password = ""
        @JsonIgnore
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

//    @Column(name = "created_at")
//    var createdAt: LocalDateTime = LocalDateTime.now()

//    @Column(name = "updated_at")
//    var updatedAt: LocalDateTime = LocalDateTime.now()
}
