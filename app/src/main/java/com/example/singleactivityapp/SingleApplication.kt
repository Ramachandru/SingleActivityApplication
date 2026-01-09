package com.example.singleactivityapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SingleApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}