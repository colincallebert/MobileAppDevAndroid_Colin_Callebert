package com.example.boerderij

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.boerderij.ui.components.Destinations
import com.example.boerderij.ui.components.MainApplication
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    private lateinit var navController: TestNavHostController

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MainApplication(navController = navController, WindowWidthSizeClass.Compact)
        }
    }

    @Test
    fun NavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Destinations.Start.name)
    }

    @Test
    fun navigateToHomeScreen() {
        composeTestRule.onNodeWithContentDescriptionId(R.string.go_to_home)
            .performClick()
    }

    @Test
    fun navigateToActivitiesScreen() {
        composeTestRule.onNodeWithContentDescriptionId(R.string.go_to_activities)
            .performClick()
    }

    @Test
    fun navigateToActivityFormScreen() {
        navigateToActivitiesScreen()
        Thread.sleep(1000)
        composeTestRule.onNodeWithTag("${"Ga naar detail"}+4")
            .performClick()
        Thread.sleep(1000)
        composeTestRule.onNodeWithTag("${"Ga naar form"}")
            .performClick()
    }

    @Test
    fun NavHost_verifyHomeButtonShownOnStartScreen() {
        val home = composeTestRule.activity.getString(R.string.go_to_home)
        composeTestRule.onNodeWithContentDescription(home).assertExists()
    }

    @Test
    fun NavHost_verifyActieButtonShownOnStartScreen() {
        val button = composeTestRule.activity.getString(R.string.go_to_activities)
        composeTestRule.onNodeWithContentDescription(button).assertExists()
    }

    @Test
    fun NavHost_verifyAboutUsButtonShownOnStartScreen() {
        val aboutus = composeTestRule.activity.getString(R.string.go_to_aboutus)
        composeTestRule.onNodeWithContentDescription(aboutus).assertExists()
    }

    @Test
    fun NavHost_clickOnActivities_navigatesToActivitiesPage() {
        navigateToHomeScreen()
        composeTestRule.onNodeWithContentDescriptionId(R.string.go_to_activities)
            .performClick()
        navController.assertCurrentRouteName(Destinations.Activities.name)
    }

    @Test
    fun NavHost_clickOnAboutUs_navigatesToAboutUsPage() {
        navigateToHomeScreen()
        composeTestRule.onNodeWithContentDescriptionId(R.string.go_to_aboutus)
            .performClick()
        navController.assertCurrentRouteName(Destinations.AboutUs.name)
    }

    @Test
    fun NavHost_clickSecondActivity_navigatesToDetailActivityPage() {
        navigateToActivitiesScreen()
        Thread.sleep(1000)
        composeTestRule.onNodeWithTag("${"Ga naar detail"}+2")
            .performClick()
        Thread.sleep(1000)
        navController.assertCurrentRouteName("${Destinations.ActivityDetail.name}/{id}")
    }

    @Test
    fun NavHost_clickGoBackArrowActivities_navigatesToHomepage() {
        navigateToHomeScreen()
        Thread.sleep(1000)
        navigateToActivitiesScreen()
        Thread.sleep(1000)
        composeTestRule.onNodeWithContentDescriptionId(R.string.go_back)
            .performClick()
        navController.assertCurrentRouteName(Destinations.Start.name)
    }


}