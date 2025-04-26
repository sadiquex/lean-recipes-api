package com.saddik.leanRecipes.repository.model

//import jakarta.persistence.Entity
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "recipes")
class RecipeDBModel : BaseEntity() {
    @Id // marks primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // let the db auto-generate the id
    var id: Long? = null
    var title: String? = ""

    var description: String = ""

    var photoUrl: String = ""


}