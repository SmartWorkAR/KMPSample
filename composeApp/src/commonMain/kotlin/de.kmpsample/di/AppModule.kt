package de.kmpsample.di

import de.kmpsample.db.Database
import de.kmpsample.ui.birddetails.BirdDetailViewModel
import de.kmpsample.ui.birdpage.BirdsRepository
import de.kmpsample.ui.birdpage.BirdsRepositoryImpl
import de.kmpsample.ui.birdpage.BirdsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

fun appModule() = module {
    single<BirdsRepository> { BirdsRepositoryImpl(get()) }
    single { provideHttpClient() }
    single { Database(get()) }

    viewModelDefinition { BirdsViewModel(get(), get()) }
    viewModelDefinition { BirdDetailViewModel(get()) }
}

private fun provideHttpClient() = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}
