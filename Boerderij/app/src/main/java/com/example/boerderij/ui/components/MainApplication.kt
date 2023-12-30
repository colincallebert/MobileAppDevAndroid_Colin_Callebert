package com.example.boerderij.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.boerderij.model.activity.Activity
import com.example.boerderij.modelview.ActivityController
import com.example.boerderij.ui.aboutUs.AboutUs
import com.example.boerderij.ui.activity.Activities
import com.example.boerderij.ui.activity.ActivityDetail
import com.example.boerderij.ui.activity.ActivityReservation
import com.example.boerderij.ui.homepage.Homepage

/**
 * Composable function for the main application.
 */
@Composable
fun MainApplication(
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass
) {
    // Retrieve the current back stack entry and check if it is the start destination
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val isStartDestination = currentBackStackEntry?.destination?.route == Destinations.Start.name

    // State to hold the list of activities
    var data by remember { mutableStateOf(emptyList<Activity>()) }

    // Fetch activities from the server when the composable is launched
    LaunchedEffect(Unit) {
        var activities = ActivityController().getActivities()
        data = activities
    }

    // Lambda functions for navigation actions
    val goHome: () -> Unit = {
        navController.popBackStack(
            Destinations.Start.name,
            inclusive = false,
        )
    }
    val goActivities: () -> Unit = {
        navController.navigate(Destinations.Activities.name)
    }
    val goDetail: (Int) -> Unit = { id ->
        navController.navigate("${Destinations.ActivityDetail.name}/$id")
    }
    val goReservation: (Int) -> Unit = { id ->
        navController.navigate("${Destinations.ActivityRegistration.name}/$id")
    }
    val goAboutUs: () -> Unit = {
        navController.navigate(Destinations.AboutUs.name)
    }

    // Determine the navigation type based on the window size
    val navigationType: AppNavigationType
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = AppNavigationType.BOTTOM_NAVIGATION
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = AppNavigationType.NAVIGATION_RAIL
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = AppNavigationType.PERMANENT_NAVIGATION_DRAWER
        }

        else -> {
            navigationType = AppNavigationType.BOTTOM_NAVIGATION
        }
    }

    // Scaffold composable that defines the overall structure of the app
    Scaffold(
        topBar = {
            // Show top bar only for BOTTOM_NAVIGATION
            if (navigationType == AppNavigationType.BOTTOM_NAVIGATION) {
                TopNavBar(
                    {
                        if (!isStartDestination) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    Icons.Filled.ArrowBack,
                                    contentDescription = "Go back"
                                )
                            }
                        }
                    },
                    onAbout = { navController.navigate(Destinations.AboutUs.name) },
                )
            }
        },
        bottomBar = {
            // Show bottom bar only for BOTTOM_NAVIGATION
            if (navigationType == AppNavigationType.BOTTOM_NAVIGATION) {
                BottomAppBar(
                    onHome = goHome,
                    onActivities = goActivities,
                    currentBackStackEntry = currentBackStackEntry?.destination?.route,
                )
            }
        },
    ) { innerPadding ->
        Row {
            // Navigation rail for MEDIUM and PERMANENT_NAVIGATION_DRAWER
            if (navigationType == AppNavigationType.NAVIGATION_RAIL || navigationType == AppNavigationType.PERMANENT_NAVIGATION_DRAWER) {
                RailAppNavigation(
                    onHome = goHome,
                    onActivities = goActivities,
                    onAboutUs = goAboutUs,
                    currentBackStackEntry = currentBackStackEntry?.destination?.route,
                )
            }

            // NavHost for handling different destinations/screens
            NavHost(
                navController = navController,
                startDestination = Destinations.Start.name,
                Modifier.padding(innerPadding),
            ) {
                composable(route = Destinations.Start.name) {
                    Homepage(activities = data, goDetail = goDetail)
                }
                composable(route = Destinations.Activities.name) {
                    Activities(activities = data, goDetail = goDetail)
                }
                composable(route = "${Destinations.ActivityRegistration.name}/{id}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                    if (id != null) {
                        ActivityReservation(id = id, goDetail = goDetail)
                    } else {
                        Text("Invalid activity ID")
                    }
                }
                composable("${Destinations.ActivityDetail.name}/{id}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                    if (id != null) {
                        val activity = data.firstOrNull { it.id == id }
                        if (activity != null) {
                            ActivityDetail(activity = activity, goRegistration = goReservation)
                        } else {
                            Text("Activity not found for ID: $id")
                        }
                    } else {
                        Text("Invalid activity ID")
                    }
                }
                composable(route = Destinations.AboutUs.name) {
                    AboutUs()
                }
            }
        }
    }
}
