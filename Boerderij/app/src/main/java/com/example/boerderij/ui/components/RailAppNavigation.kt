package com.example.boerderij.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.boerderij.R

/**
 * Composable function for the railnavigation.
 *
 * @param onHome The action to perform when the home icon is clicked.
 * @param onActivities The action to perform when the activities icon is clicked.
 * @param onAboutUs The action to perform when the about us icon is clicked.
 * @param currentBackStackEntry The current back stack entry.
 */
@Composable
fun RailAppNavigation(
    onHome: () -> Unit,
    onActivities: () -> Unit,
    onAboutUs: () -> Unit,
    currentBackStackEntry: String?,
) {
    // Define the NavigationRail
    NavigationRail {
        // NavigationRailItem for Home destination
        NavigationRailItem(
            selected = currentBackStackEntry == Destinations.Start.name,
            onClick = onHome,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(R.string.go_to_home)
                )
            }
        )

        // NavigationRailItem for Activities destination
        NavigationRailItem(
            selected = currentBackStackEntry == Destinations.Activities.name,
            onClick = onActivities,
            icon = {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = stringResource(R.string.go_to_activities)
                )
            }
        )

        // NavigationRailItem for AboutUs destination
        NavigationRailItem(
            selected = currentBackStackEntry == Destinations.AboutUs.name,
            onClick = onAboutUs,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Group,
                    contentDescription = stringResource(R.string.go_to_aboutus),
                )
            }
        )
    }
}
