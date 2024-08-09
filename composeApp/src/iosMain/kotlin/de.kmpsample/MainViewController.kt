package de.kmpsample

import androidx.compose.ui.window.ComposeUIViewController
import de.kmpsample.di.appModule
import de.kmpsample.di.platformModule
import de.kmpsample.ui.App
import org.koin.core.context.startKoin

class MainViewController {

    fun mainViewController() = ComposeUIViewController { App() }

    fun initKoin() = startKoin { modules(appModule() + platformModule()) }
}