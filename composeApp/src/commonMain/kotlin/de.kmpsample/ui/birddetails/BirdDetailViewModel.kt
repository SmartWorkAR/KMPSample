package de.kmpsample.ui.birddetails

import de.kmpsample.db.Database
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.compose.koinInject

class BirdDetailViewModel(private val db: Database) : ViewModel() {

    fun getBirdFromDb(id: Int) = db.getBird(id.toLong())

    fun setIsFavorite(id: Int, isFavorite: Boolean) = db.setIsFavorite(id.toLong(), isFavorite)
}