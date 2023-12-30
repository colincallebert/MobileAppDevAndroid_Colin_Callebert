package com.example.boerderij.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Info
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


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopNavBar(
    navigationIcon: @Composable () -> Unit,
    onAbout: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,

            ),
        title = {
            Text(stringResource(R.string.app_name))
        },
        navigationIcon = navigationIcon,
        actions = {
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
