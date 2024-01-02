package com.example.boerderij.model.registration

import kotlinx.serialization.Serializable

/**
 * Serializable data class representing a registration.
 *
 * @property userid The unique identifier for the user associated with the registration. Is always 1 because there is no login on the app.
 * @property activityid The unique identifier for the activity associated with the registration.
 * @property amount The amount associated with the registration.
 */
@Serializable
data class Registration(
    val userid: Int,
    val activityid: Int,
    var amount: Int
)
