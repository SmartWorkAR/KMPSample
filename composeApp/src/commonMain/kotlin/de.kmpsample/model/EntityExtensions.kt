package de.kmpsample.model

import de.kmpsample.DbBird

fun DbBird.toBird() = Bird(
    id = id.toInt(),
    author = author,
    category = category,
    path = image,
    isFavorite = isFavorite
)