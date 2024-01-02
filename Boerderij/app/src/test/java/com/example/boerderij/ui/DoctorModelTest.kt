package com.example.boerderij.ui

import com.example.boerderij.fake.FakeApiAppRepository
import com.example.boerderij.network.doctorApi.DoctorApiState
import com.example.boerderij.viewmodel.DoctorViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DoctorModelTest {

    private lateinit var viewModel: DoctorViewModel

    @get:Rule
    val testDispatcherRule = TestDispatchersRule()

    @Before
    fun setup() {
        viewModel = DoctorViewModel(FakeApiAppRepository())
    }

    @Test
    fun getDoctors_methodCall_StateIsSuccessAfterCall() {
        // Arrange
        val expectedNewsApiState = DoctorApiState.Success

        // Act
        viewModel.getDoctors()

        // Assert
        Assert.assertEquals(expectedNewsApiState, viewModel.doctorsApiState)
    }

    @Test
    fun getNewsArticlesDetails_methodCall_StateIsSuccessAfterCall() {

        viewModel.getDoctorDetail(1)


    }

}