package de.kmpsample.model

import kotlinx.serialization.Serializable

@Serializable
data class Bird(
    val id: Int? = null,
    val author: String,
    val category: String,
    val path: String,
    val isFavorite: Boolean = false
)
