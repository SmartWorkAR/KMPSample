package de.kmpsample.ui.birdpage

import de.kmpsample.db.Database
import de.kmpsample.db.toDbBirdEntity
import de.kmpsample.model.Bird
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BirdsViewModel(
    private val birdsRepository: BirdsRepository,
    private val db: Database
) : ViewModel() {
    private val _uiState = MutableStateFlow(BirdsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        updateImages()
    }

    override fun onCleared() {
        birdsRepository.closeHttpClient()
    }

    fun selectCategory(category: String?) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
    }

    private fun updateImages() = viewModelScope.launch {
        db.getBirds().ifEmpty {
            db.saveBirds(birdsRepository.getImages().map { it.toDbBirdEntity() })
        }
        _uiState.update {
            it.copy(images = db.getBirds())
        }
    }
}

data class BirdsUiState(
    val images: List<Bird> = emptyList(),
    val selectedCategory: String? = null
) {
    val categories = images.map { it.category }.toSet()
    val selectedImages = if (selectedCategory != "favorite") {
        images.filter { it.category == selectedCategory }
    } else {
        images.filter { it.isFavorite }
    }
}