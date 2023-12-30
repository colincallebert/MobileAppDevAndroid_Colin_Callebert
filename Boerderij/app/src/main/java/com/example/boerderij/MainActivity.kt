package com.example.boerderij

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.boerderij.ui.components.MainApplication
import com.example.boerderij.ui.theme.BoerderijTheme

/**
 * [MainActivity] is the main entry point for the Android application.
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up the content of the activity using Jetpack Compose
        setContent {
            // Apply the BoerderijTheme as the overall theme for the application
            BoerderijTheme {
                // Create a Surface that fills the entire size of the activity
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Calculate the window size class using Jetpack Compose's window size class API
                    val windowSize = calculateWindowSizeClass(this)

                    // Pass the calculated window size to the MainApplication composable
                    MainApplication(windowSize = windowSize.widthSizeClass)
                }
            }
        }
    }
}

/**
 * [Greeting] is a simple composable that displays a greeting with a given name.
 * @param name The name to be included in the greeting.
 * @param modifier Optional modifier for styling.
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Display a text greeting with the provided name
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}