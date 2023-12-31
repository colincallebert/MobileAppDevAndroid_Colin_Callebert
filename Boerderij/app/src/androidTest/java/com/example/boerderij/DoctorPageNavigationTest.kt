package com.example.boerderij


import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.boerderij.ui.components.MainApplication
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DoctorPageNavigationTest {

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
    fun NavHost_clickOnDoctor_navigatesToDetailDoctorPage() {
        composeTestRule.onNodeWithContentDiscripitionId(R.string.go_to_team)
            .performClick()
        Thread.sleep(1000)
        composeTestRule.onNodeWithTag("${"Meer info"}+1")
            .performClick()
        //timer
        Thread.sleep(1000)
        composeTestRule.onNodeWithText("Ozlem Kose")
            .assertIsDisplayed()
    }


}