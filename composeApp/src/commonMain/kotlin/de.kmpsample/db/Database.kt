package de.kmpsample.db

import de.kmpsample.BirdDb
import de.kmpsample.model.Bird
import de.kmpsample.model.toBird

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = BirdDb(databaseDriverFactory.createDriver())
    private val dbQuery = database.birdQueries

    fun getBirds(): List<Bird> = dbQuery.getBirds().executeAsList().map { it.toBird() }

    fun getBird(id: Long): Bird = dbQuery.getBird(id).executeAsOne().toBird()

    fun saveBirds(birds: List<Bird>) {
        birds.forEach { bird ->
            dbQuery.saveBird(
                id = null,
                image = bird.path,
                category = bird.category,
                author = bird.author
            )
        }
    }

    fun setIsFavorite(id: Long, isFavorite: Boolean) = dbQuery.setIsFavorite(isFavorite, id)
}