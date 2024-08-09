package de.kmpsample.db

import de.kmpsample.model.Bird

fun Bird.toDbBirdEntity() = Bird(
    id = -1,
    path = path,
    category = category,
    author = author,
    isFavorite = isFavorite
)