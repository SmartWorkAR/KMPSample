package de.kmpsample.ui.birddetails

import androidx.compose.runtime.Composable
import org.koin.compose.koinInject

object BirdDetailNavigation {
    const val route = "birdsDetail"
}

@Composable
fun BirdDetailNavigation(
    viewModel: BirdDetailViewModel = koinInject(),
    birdId: Int,
    onClickBack: () -> Unit
) {
    val bird = viewModel.getBirdFromDb(birdId)
    BirdDetailScreen(
        bird = bird,
        onClickBack = onClickBack
    )
}