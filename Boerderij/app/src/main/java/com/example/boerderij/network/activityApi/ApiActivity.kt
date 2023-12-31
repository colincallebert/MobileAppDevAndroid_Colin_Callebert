package com.example.boerderij.network.activityApi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

/**
 * Data class representing the API response for activity data.
 *
 * @property activities A list of [Activity] objects representing medical activities.
 */
@Serializable
data class ApiActivityResponse(
    val activities: List<Activity>
)

/**
 * Data class representing an activity.
 */
@Serializable
data class Activity(
    val id: Int,
    val title: String,
    val starttime: String,
    val endtime: String,
    val description: String,
    val maxregistrations: Int,
    val amount: Int
)

/**
 * Extension function to convert a flow of [ApiActivitiesResponse] into a flow of domain objects.
 *
 * @receiver A Flow of [ApiActivitiesResponse].
 * @return A Flow of a list of [Activity] domain objects.
 */
fun Flow<ApiActivityResponse>.asDomainObjects(): Flow<List<Activity>> {
    return map {
        it.asDomainObjects()
    }
}

/**
 * Converts an [ApiConditionResponse] to a list of domain object [Condition].
 *
 * @receiver An instance of [ApiConditionResponse].
 * @return A list of [Condition] domain objects.
 */
fun ApiActivityResponse.asDomainObjects(): List<Activity> {
    return activities.map {
        Activity(
            id = it.id,
            title = it.title,
            starttime = it.starttime,
            endtime = it.endtime,
            description = it.description,
            maxregistrations = it.maxregistrations,
            amount = it.amount
        )
    }
}
