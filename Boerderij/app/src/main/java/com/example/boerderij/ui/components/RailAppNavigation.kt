package com.example.boerderij.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.boerderij.R

@Composable
fun RailAppNavigation(
    onHome: () -> Unit,
    onActivities: () -> Unit,
    onAboutUs: () -> Unit,
    currentBackStackEntry: String?,
) {

    NavigationRail {
        NavigationRailItem(
            selected = currentBackStackEntry == Destinations.Start.name,
            onClick = onHome,
            icon = {
                Icon(
                    imageVector =  Icons.Filled.Home,
                    contentDescription = stringResource(R.string.go_to_home)
                )
            }
        )
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
        NavigationRailItem (
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
