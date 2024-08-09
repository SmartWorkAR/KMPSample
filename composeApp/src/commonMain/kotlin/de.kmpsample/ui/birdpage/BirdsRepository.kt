package de.kmpsample.ui.birdpage

import de.kmpsample.model.Bird

interface BirdsRepository {

    suspend fun getImages(): List<Bird>

    fun closeHttpClient()
}