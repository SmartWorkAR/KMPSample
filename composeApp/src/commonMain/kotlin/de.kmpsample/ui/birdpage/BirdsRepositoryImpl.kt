package de.kmpsample.ui.birdpage

import de.kmpsample.model.Bird
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.setBody

class BirdsRepositoryImpl(private val httpClient: HttpClient) : BirdsRepository {

    override suspend fun getImages(): List<Bird> {
        return httpClient
            .get("https://sebi.io/demo-image-api/pictures.json")
            .body<List<Bird>>()
    }

    override fun closeHttpClient() = httpClient.close()
}