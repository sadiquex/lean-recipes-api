package com.saddik.leanRecipes.repository.model

import jakarta.persistence.*
import kotlin.collections.ArrayList
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "recipes")
@EntityListeners(AuditingEntityListener::class)
class RecipeDBModel : BaseEntity() {
    @Id // marks primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // let the db auto-generate the id
    var id: Long? = null

    var title: String? = ""

    var description: String = ""

    var photoUrl: String = ""

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = [JoinColumn(name = "recipe_id")])
    @Column(name = "ingredient")
    var ingredients: List<String> = ArrayList()

//    The recipe_id foreign key in recipe_ingredients and recipe_directions lets each ingredient or step know which recipe it belongs to.

    @ElementCollection
    @CollectionTable(name = "recipe_directions", joinColumns = [JoinColumn(name = "recipe_id")])
    @Column(name = "direction")
    var directions: List<String> = ArrayList()

    var servings: Int = 1

    var yield: String? = null

    var prepTimeMinutes: Int = 0

    var cookTimeMinutes: Int? = null

}
