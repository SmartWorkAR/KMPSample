package de.kmpsample.ui

import androidx.compose.runtime.Composable
import de.kmpsample.BirdAppTheme
import moe.tlaster.precompose.PreComposeApp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PreComposeApp {
        BirdAppTheme {
            BirdScreen()
        }
    }
}
