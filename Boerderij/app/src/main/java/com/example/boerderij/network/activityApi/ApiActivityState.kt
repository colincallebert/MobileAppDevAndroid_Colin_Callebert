package com.example.boerderij.network.activityApi

import com.example.boerderij.model.activity.ActivityDetail

/**
 * A sealed interface representing the possible states of a generic activity API call.
 */
sealed interface ActivitiesApiState {
    /**
     * Represents the loading state of an API call, indicating that the data is currently being fetched.
     */
    object Loading : ActivitiesApiState

    /**
     * Represents an error state of an API call, indicating that an error occurred during the data fetching process.
     */
    object Error : ActivitiesApiState

    /**
     * Represents the success state of an API call, indicating that the data was successfully fetched.
     */
    object Success : ActivitiesApiState
}

sealed interface ActivityDetailApiState {
    /**
     * Represents the loading state of an API call for activity details, indicating ongoing data retrieval.
     */
    object Loading : ActivityDetailApiState

    /**
     * Represents the success state of an API call for activity details.
     *
     * @property activity An optional [ActivityDetail] object containing detailed information about a medical activity.
     */
    data class Success(val activity: Activity?) : ActivityDetailApiState

    /**
     * Represents an error state of an API call for activity details, indicating that an error occurred during the process.
     */
    object Error : ActivityDetailApiState
}

/**
 * Data class representing the state of a list of conditions fetched from the API.
 *
 * @property activityList A list of [Activity] objects, defaulting to an empty list if no conditions are fetched.
 */
data class ActivityListState(val activityList: List<Activity> = listOf())
