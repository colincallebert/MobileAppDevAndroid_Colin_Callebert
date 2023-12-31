package com.example.boerderij

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipe
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.action.GeneralLocation
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.size.Size
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
        composeTestRule.onNodeWithText("Home")
            .performClick()
    }

    @Test
    fun navigateToActivitiesScreen() {
        composeTestRule.onNodeWithText("Registraties")
            .performClick()
    }

    @Test
    fun NavHost_verifyHomeButtonShownOnStartScreen() {
        composeTestRule.onNodeWithText("Home").assertExists()
    }

    @Test
    fun NavHost_verifyActieButtonShownOnStartScreen() {
        composeTestRule.onNodeWithText("Registraties").assertExists()
    }

    @Test
    fun NavHost_verifyAboutUsButtonShownOnStartScreen() {
        val aboutus = composeTestRule.activity.getString(R.string.go_to_aboutus)
        composeTestRule.onNodeWithContentDescription(aboutus).assertExists()
    }

    @Test
    fun NavHost_clickOnActivities_navigatesToActivitiesPage() {
        navigateToHomeScreen()
        composeTestRule.onNodeWithText("Registraties")
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