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

@Composable
fun BottomAppBar(
    onHome: () -> Unit,
    onActivities: () -> Unit,
    currentBackStackEntry: String?,
) {
    val context = LocalContext.current

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            NavigationBarItem(
                selected = currentBackStackEntry == Destinations.Start.name,
                onClick = onHome,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = stringResource(R.string.go_to_home)
                    )
                })

            NavigationBarItem(
                selected = currentBackStackEntry == Destinations.Activities.name,
                onClick = onActivities,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = stringResource(R.string.go_to_activities)
                    )

                },

                )
        })
}