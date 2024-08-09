package de.kmpsample.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import de.kmpsample.ui.birddetails.BirdDetailNavigation
import de.kmpsample.ui.birdpage.BirdPageNavigation
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.query
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun BirdScreen() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = BirdPageNavigation.route,
    ) {
        scene(route = BirdPageNavigation.route) {
            var selectedCategory by rememberSaveable { mutableStateOf<String?>(null) }
            BirdPageNavigation(
                savedCategory = selectedCategory,
                saveCategory = { selectedCategory = it },
                navigateToDetail = { birdId ->
                    val jsonBirdId = Json.encodeToString(birdId)
                    navigator.navigate("${BirdDetailNavigation.route}?birdId=$jsonBirdId")
                }
            )
        }
        scene(route = BirdDetailNavigation.route) { backStackEntry ->
            val birdId: Int = Json.decodeFromString(backStackEntry.query<String>("birdId") ?: "")
            BirdDetailNavigation(
                birdId = birdId,
                onClickBack = { navigator.popBackStack() }
            )
        }
    }
}