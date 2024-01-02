package com.example.boerderij.ui

import com.example.boerderij.fake.FakeApiAppRepository
import com.example.boerderij.network.conditionApi.ConditionApiState
import com.example.boerderij.viewmodel.ConditionViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ConditionModelTest {

    private lateinit var viewModel: ConditionViewModel

    @get:Rule
    val testDispatcherRule = TestDispatchersRule()

    @Before
    fun setup() {
        viewModel = ConditionViewModel(FakeApiAppRepository())
    }

    @Test
    fun getDoctors_methodCall_StateIsSuccessAfterCall() {
        // Arrange
        val expectedNewsApiState = ConditionApiState.Success

        // Act
        viewModel.getConditions()

        // Assert
        Assert.assertEquals(expectedNewsApiState, viewModel.conditionApiState)
    }

    @Test
    fun getNewsArticlesDetails_methodCall_StateIsSuccessAfterCall() {

        viewModel.getConditionDetail(1)


    }


}