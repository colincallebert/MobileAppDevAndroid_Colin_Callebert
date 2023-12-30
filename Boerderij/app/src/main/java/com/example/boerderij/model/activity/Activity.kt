package com.example.boerderij.model.activity

import kotlinx.serialization.Serializable

/**
 * Represents the response structure for API activities.
 *
 * @property items List of [Activity] objects.
 * @property count Total count of activities in the response.
 */
@Serializable
data class ApiActivityResponse(
    val items: List<Activity>,
    val count: Int
)

/**
 * Represents an activity in the system.
 *
 * @property id Unique identifier for the activity.
 * @property title Title of the activity.
 * @property starttime Start time of the activity.
 * @property endtime End time of the activity.
 * @property description Description of the activity.
 * @property maxregistrations Maximum number of registrations allowed for the activity.
 * @property amount Amount associated with the activity.
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