package de.kmpsample.ui.birdpage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.koinInject

object BirdPageNavigation {
    const val route = "birdPage"
}

@Composable
fun BirdPageNavigation(
    viewModel: BirdsViewModel = koinInject(),
    savedCategory: String?,
    saveCategory: (String?) -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.selectCategory(savedCategory)
    BirdsPage(
        uiState = uiState,
        selectCategory = { category ->
            viewModel.selectCategory(category)
            saveCategory(category)
        },
        navigateToDetail = { id -> navigateToDetail(id) })
}