package com.example.boerderij

import android.app.Application
import com.example.boerderij.data.AppContainer
import com.example.boerderij.data.DefaultAppContainer

/**
 * Custom Application class for the Android application.
 * It extends the Android Application class and is used to initialize global application state before the first activity is displayed.
 * Here, it initializes the AppContainer which will hold and manage app-wide dependencies.
 */
class AppApplication : Application() {

    // Lateinit var used for deferred initialization of the AppContainer.
    lateinit var container: AppContainer

    /**
     * Called when the application is starting, before any activities, services, or receivers have been created.
     * Initializes the container with DefaultAppContainer, providing a concrete implementation of AppContainer to manage dependencies.
     */
    override fun onCreate() {
        super.onCreate()
        // Initialize the container to hold app-wide dependencies.
        container = DefaultAppContainer(applicationContext)
    }
}
