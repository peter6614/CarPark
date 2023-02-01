package com.peter.carpark.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CarParkApplication : Application() {

    override fun onCreate() {
        super.onCreate() // Injection happens in super.onCreate()
        // Use bar
    }
}