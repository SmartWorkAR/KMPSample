package de.kmpsample.ui.birddetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.kmpsample.model.Bird
import de.kmpsample.ui.birdpage.BirdsViewModel
import de.kmpsample.ui.birdpage.SkeletonImage
import de.kmpsample.ui.bounceClick
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kmpsample.composeapp.generated.resources.Res
import kmpsample.composeapp.generated.resources.author
import kmpsample.composeapp.generated.resources.category
import kmpsample.composeapp.generated.resources.ic_arrow_back
import kmpsample.composeapp.generated.resources.ic_favorite_filled
import kmpsample.composeapp.generated.resources.ic_favorite_nofill
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BirdDetailScreen(
    bird: Bird,
    viewModel: BirdDetailViewModel = koinInject(),
    onClickBack: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(bird.isFavorite) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            BackButton(modifier = Modifier.padding(16.dp), onClickBack = { onClickBack() })
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(Modifier.height(340.dp)) {
                    KamelImage(
                        resource = asyncPainterResource("https://sebi.io/demo-image-api/${bird.path}"),
                        contentDescription = "${bird.category} by ${bird.author}",
                        onLoading = { SkeletonImage() },
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(320.dp).fillMaxWidth().align(Alignment.TopCenter)
                    )
                    FavoriteButton(
                        isFavorite = isFavorite,
                        setIsFavorite = {
                            isFavorite = !isFavorite
                            if (bird.id != null) {
                                viewModel.setIsFavorite(bird.id, isFavorite)
                            }
                        },
                        modifier = Modifier.align(Alignment.BottomEnd).padding(end = 24.dp)
                    )
                }
                Spacer(Modifier.height(24.dp))
                Text(
                    text = stringResource(Res.string.category),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Text(text = bird.category)
                Spacer(Modifier.height(24.dp))
                Text(
                    text = stringResource(Res.string.author),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Text(text = bird.author)
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    setIsFavorite: () -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier.padding(horizontal = 10.dp)) {
        Button(
            onClick = {},
            shape = CircleShape,
            elevation = null,
            border = BorderStroke(2.dp, Color.White),
            modifier = Modifier.size(56.dp),
            contentPadding = PaddingValues(1.dp)
        ) {
            Icon(
                painter = painterResource(
                    if (isFavorite) {
                        Res.drawable.ic_favorite_filled
                    } else {
                        Res.drawable.ic_favorite_nofill
                    }
                ),
                contentDescription = "Favorite",
                modifier = Modifier
                    .size(32.dp)
                    .bounceClick { setIsFavorite() }
            )
        }

    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun BackButton(modifier: Modifier, onClickBack: () -> Unit) {
    IconButton(
        modifier = modifier.defaultMinSize(40.dp),
        onClick = onClickBack,
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_arrow_back),
            contentDescription = "back",
        )
    }
}
