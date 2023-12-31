package com.example.boerderij

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.boerderij.ui.components.MainApplication
import com.example.boerderij.ui.theme.AppTheme

/**
 * Main activity for the application. This is the entry point for the app's UI.
 * It sets up the Compose UI content inside the activity.
 * Extends ComponentActivity, which provides compatibility features and lifecycle methods for handling Android components.
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting. This is where most initialization should go: calling setContentView(int) to inflate
     * the activity's UI, using findViewById(int) to programmatically interact with widgets in the UI, calling managedQuery(Uri, String[], String, String[], String)
     * to retrieve cursors for data being displayed, etc.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most
     * recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set the content for the activity using Compose UI.
            AppTheme {
                // AppTheme defines the overall theme for the application (colors, typography, etc.)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    // Surface is a foundational Compose layout that handles background color and elevation.
                    MainApplication(windowSize = windowSize.widthSizeClass)
                    // MainApplication is the root composable function that sets up the initial UI of the app.
                }
            }
        }
    }
}
