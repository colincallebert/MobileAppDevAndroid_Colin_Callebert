package com.example.boerderij.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.boerderij.R

/**
 * Composable function for the bottom app bar.
 *
 * @param onHome The action to perform when the home icon is clicked.
 * @param onActivities The action to perform when the activities icon is clicked.
 * @param currentBackStackEntry The current back stack entry.
 */
@Composable
fun BottomAppBar(
    onHome: () -> Unit,
    onActivities: () -> Unit,
    currentBackStackEntry: String?,
) {
    // Create a BottomAppBar with navigation items
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        // Set up navigation items as actions in the bottom app bar
        actions = {
            // Navigation item for the home screen
            NavigationBarItem(
                // Check if the current screen is the home screen
                selected = currentBackStackEntry == Destinations.Start.name,
                // Define the action to perform when clicked
                onClick = onHome,
                // Icon for the home screen
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = stringResource(R.string.go_to_home)
                    )
                }
            )

            // Navigation item for the activities screen
            NavigationBarItem(
                // Check if the current screen is the activities screen
                selected = currentBackStackEntry == Destinations.Activities.name,
                // Define the action to perform when clicked
                onClick = onActivities,
                // Icon for the activities screen
                icon = {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = stringResource(R.string.go_to_activities)
                    )
                }
            )
        }
    )
}