package com.example.boerderij.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.boerderij.R

/**
 * Composable function for the top app bar.
 *
 * @param navigationIcon The action to perform when the navigation icon is clicked.
 * @param onAbout The action to perform when the about us icon is clicked.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopNavBar(
    navigationIcon: @Composable () -> Unit,
    onAbout: () -> Unit,
) {
    // Create a TopAppBar with specified colors
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        // Set the title of the app
        title = {
            Text(stringResource(R.string.app_name))
        },
        // Set the navigation icon and actions
        navigationIcon = navigationIcon,
        actions = {
            // Add an action (AboutUs) to the app bar
            IconButton(onAbout) {
                Icon(
                    Icons.Filled.Group,
                    contentDescription = stringResource(R.string.go_to_aboutus),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        },
    )
}
