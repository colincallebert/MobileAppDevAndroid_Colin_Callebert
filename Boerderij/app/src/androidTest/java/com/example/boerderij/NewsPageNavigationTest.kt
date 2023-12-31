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
class NewsPageNavigationTest {

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
    fun NavHost_clickOnNewsItem_navigatesToDetailNewsPage() {
        composeTestRule.onNodeWithContentDiscripitionId(R.string.go_to_home)
            .performClick()
        Thread.sleep(1000)
        composeTestRule.onNodeWithTag("${"Meer info"}+1")
            .performClick()
        //timer
        Thread.sleep(1000)
        composeTestRule.onNodeWithText("Onderzoekers hebben onlangs een doorbraak gemaakt in de behandeling van staar." +
                " Een nieuw geneesmiddel is ontdekt dat de troebeling van de lens in het oog effectief kan verminderen.")
            .assertIsDisplayed()
    }



}