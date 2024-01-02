package com.example.boerderij.ui

import com.example.boerderij.fake.FakeApiAppRepository
import com.example.boerderij.network.newsApi.NewsApiState
import com.example.boerderij.viewmodel.NewsViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NewsModelTest {
    private lateinit var viewModel: NewsViewModel

    @get:Rule
    val testDispatcherRule = TestDispatchersRule()

    @Before
    fun setup() {
        viewModel = NewsViewModel(FakeApiAppRepository())
    }

    @Test
    fun getNewsArticles_methodCall_StateIsSuccessAfterCall() {
        // Arrange
        val expectedNewsApiState = NewsApiState.Success

        // Act
        viewModel.getApiNews()

        // Assert
        Assert.assertEquals(expectedNewsApiState, viewModel.newsApiState)
    }

    @Test
    fun getNewsArticlesDetails_methodCall_StateIsSuccessAfterCall() {

        viewModel.getNewsDetail(1)

    }


}