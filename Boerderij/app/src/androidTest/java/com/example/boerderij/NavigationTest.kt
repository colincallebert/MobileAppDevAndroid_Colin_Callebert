package com.example.boerderij


import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
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

    private fun navigateToNewsScreen() {
    composeTestRule.onNodeWithContentDiscripitionId(R.string.go_to_home)
            .performClick()

    }

    @Test
    fun NavHost_verifyInfoButtonShownOnStartScreen() {
        val aboutus = composeTestRule.activity.getString(R.string.go_to_aboutus)
        composeTestRule.onNodeWithContentDescription(aboutus).assertExists()
    }
    @Test
    fun NavHost_verifyCalendarButtonShownOnStartScreen() {
        val button = composeTestRule.activity.getString(R.string.go_to_calendar)
        composeTestRule.onNodeWithContentDescription(button).assertExists()
    }
    @Test
    fun NavHost_verifyTeamButtonShownOnStartScreen() {
        val button = composeTestRule.activity.getString(R.string.go_to_team)
        composeTestRule.onNodeWithContentDescription(button).assertExists()
    }
    @Test
    fun NavHost_verifyConditoinButtonShownOnStartScreen() {
        val button = composeTestRule.activity.getString(R.string.go_to_conditions)
        composeTestRule.onNodeWithContentDescription(button).assertExists()
    }
    @Test
    fun NavHost_verifyHomeButtonShownOnStartScreen() {
        val button = composeTestRule.activity.getString(R.string.go_to_home)
        composeTestRule.onNodeWithContentDescription(button).assertExists()
    }

    @Test
    fun NavHost_clickOnConditionsScreen_navigatesToConditionsScreenn() {
        navigateToNewsScreen()
        composeTestRule.onNodeWithContentDiscripitionId(R.string.go_to_conditions)
            .performClick()
        navController.assertCurrentRouteName(Destinations.ConditionOverview.name)
    }

    @Test
    fun NavHost_clickOnTeamScreen_navigatesToTeamScreen() {
        navigateToNewsScreen()
        composeTestRule.onNodeWithContentDiscripitionId(R.string.go_to_team)
            .performClick()
        navController.assertCurrentRouteName(Destinations.Team.name)
    }


    @Test
    fun NavHost_clickOnAboutUsScreen_navigatesToAboutUsScreen() {
        navigateToNewsScreen()
        composeTestRule.onNodeWithContentDiscripitionId(R.string.go_to_aboutus)
            .performClick()
        navController.assertCurrentRouteName(Destinations.AboutUs.name)
    }



}