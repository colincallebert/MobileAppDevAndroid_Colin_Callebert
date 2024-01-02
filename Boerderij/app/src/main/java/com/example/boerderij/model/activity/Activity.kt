package com.example.boerderij.model.activity

import kotlinx.serialization.Serializable

/**
 * Serializable data class representing an activity.
 *
 * @property id The unique identifier for the activity.
 * @property title The title of the activity.
 * @property starttime The start time of the activity.
 * @property endtime The end time of the activity.
 * @property description The description of the activity. This contains a short description, long description and imageURI separated by a |
 * @property maxregistrations The maximum number of registrations allowed for the activity.
 * @property amount The amount of registrations for the activity.
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
