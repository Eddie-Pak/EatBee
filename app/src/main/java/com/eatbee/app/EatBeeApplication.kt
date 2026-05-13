package com.eatbee.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EatBeeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}