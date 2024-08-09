package de.kmpsample.ui.birdpage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import de.kmpsample.model.Bird
import de.kmpsample.shimmerBackground
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kmpsample.composeapp.generated.resources.Res
import kmpsample.composeapp.generated.resources.all
import kmpsample.composeapp.generated.resources.category
import kmpsample.composeapp.generated.resources.favorite
import kmpsample.composeapp.generated.resources.ic_favorite_filled
import kmpsample.composeapp.generated.resources.loading_failed
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BirdsPage(
    uiState: BirdsUiState,
    selectCategory: (String?) -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(top = 24.dp, start = 8.dp, end = 8.dp, bottom = 16.dp)
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CategoryButton(stringResource(Res.string.all)) { selectCategory(null) }
            CategoryButton(stringResource(Res.string.favorite)) { selectCategory("favorite") }
            for (category in uiState.categories) {
                CategoryButton(category) { selectCategory(category) }
            }
        }
        AnimatedVisibility(uiState.images.isNotEmpty()) {
            val birds =
                if (uiState.selectedCategory == null) uiState.images else uiState.selectedImages
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
                content = {
                    items(birds) { bird ->
                        BirdImageCell(
                            bird = bird,
                            navigateToDetail = { bird.id?.let { navigateToDetail(it) } }
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun CategoryButton(title: String, onClickCategory: () -> Unit) {
    OutlinedButton(
        onClick = onClickCategory,
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
    ) {
        Text(title)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun BirdImageCell(bird: Bird, navigateToDetail: () -> Unit) {
    Box {
        KamelImage(
            resource = asyncPainterResource("https://sebi.io/demo-image-api/${bird.path}"),
            contentDescription = "${bird.category} by ${bird.author}",
            onLoading = { SkeletonImage() },
            onFailure = {
                Image(
                    painter = painterResource(Res.drawable.loading_failed),
                    contentDescription = ""
                )
            },
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.0f)
                .clip(RoundedCornerShape(12.dp))
                .clickable { navigateToDetail() }
        )
        if (bird.isFavorite) {
            Icon(
                painter = painterResource(Res.drawable.ic_favorite_filled),
                tint = Color.White,
                contentDescription = "favorite",
                modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
            )
        }
    }
}

@Composable
fun SkeletonImage() {
    Box(Modifier.fillMaxWidth().aspectRatio(1.0f).shimmerBackground())
}