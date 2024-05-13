package com.demo.testingproject

import android.app.Application
import com.demo.testingproject.di.appModule
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}