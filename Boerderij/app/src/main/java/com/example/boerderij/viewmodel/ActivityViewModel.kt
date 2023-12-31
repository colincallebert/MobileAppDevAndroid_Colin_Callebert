package com.example.boerderij.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.boerderij.AppApplication
import com.example.boerderij.data.AppRepository
import com.example.boerderij.model.registration.Registration
import com.example.boerderij.network.activityApi.ActivitiesApiState
import com.example.boerderij.network.activityApi.ActivityListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * ViewModel for managing activity data within the application.
 * It handles fetching and maintaining the state of the list of activities.
 *
 * @property appRepository The repository to interact with the data layer of the application.
 */
class ActivityViewModel(
    private val appRepository: AppRepository
) : ViewModel() {
    // StateFlow to hold and emit UI state updates for the overview of activities
    private val _uiState = MutableStateFlow(
        ActivityState(
            listOf()
        )
    )
    val uiState: StateFlow<ActivityState> = _uiState.asStateFlow()

    // StateFlow to hold and emit the list of activities
    lateinit var uiActivityListState: StateFlow<ActivityListState>

    // MutableState to hold and emit the API state for fetching activities
    var activityApiState: ActivitiesApiState by mutableStateOf(ActivitiesApiState.Loading)
        private set

    init {
        getActivities()
    }

    /**
     * Fetches and updates the list of activities from the repository.
     * Sets the activity list state and updates the UI state accordingly.
     */
    fun getActivities() {
        try {
            viewModelScope.launch { appRepository.refreshActivities() }
            uiActivityListState = appRepository.getActivities().map { ActivityListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(500_000L),
                    initialValue = ActivityListState(listOf())
                )
            activityApiState = ActivitiesApiState.Success
        } catch (e: SocketTimeoutException) {
            activityApiState = ActivitiesApiState.Error
        } catch (e: IOException) {
            activityApiState = ActivitiesApiState.Error
        }
    }

    /**
     * Registers a user for a specific activity with the provided ID and amount.
     *
     * @param activityId The ID of the activity to register for.
     * @param amount The amount of people to register.
     */
    fun registreren(activityId: Int, amount: Int) {
        val registration = Registration(
            userid = 1,
            activityid = activityId,
            amount = amount
        )
        try {
            viewModelScope.launch { appRepository.createRegistration(registration) }
        } catch (e: Exception) {
            // Handle exceptions if necessary
        }
    }

    /**
     * Deletes a registration with the provided ID.
     *
     * @param id The ID of the registration to be deleted.
     */
    fun deleteRegistration(id: Int) {
        try {
            viewModelScope.launch { appRepository.deleteRegistration(id) }
        } catch (e: Exception) {
            // Handle exceptions if necessary
        }
    }

    // Factory for creating instances of ActivityViewModel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AppApplication)
                val appRepository = application.container.appRepository
                ActivityViewModel(appRepository)
            }
        }
    }
}
