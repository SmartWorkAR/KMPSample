package de.kmpsample

import android.app.Application
import de.kmpsample.di.appModule
import de.kmpsample.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(appModule() + platformModule())
        }
    }
}