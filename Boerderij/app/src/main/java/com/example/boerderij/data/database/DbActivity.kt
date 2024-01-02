package com.example.boerderij.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boerderij.network.activityApi.Activity

/**
 * Represents an activity entity as a table in the database using Room.
 * This class will be used to create a table for storing activities.
 */
@Entity
data class DbActivity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val starttime: String,
    val endtime: String,
    val description: String,
    val maxregistrations: Int,
    var amount: Int
)

/**
 * Converts a network model [Activity] object to a database entity [DbActivity].
 * This function is useful for transforming data received from a network call into a format that can be stored locally.
 * Is mapped 1:1 with the database table. But could be different.
 * @return [DbActivity] The corresponding database entity with data from the [Activity].
 */
fun Activity.asDbActivity() = DbActivity(
    id = id,
    title = title,
    starttime = starttime,
    endtime = endtime,
    description = description,
    maxregistrations = maxregistrations,
    amount = amount
)

/**
 * Converts this [DbActivity] database entity to a domain model [Activity].
 * This function is useful for transforming database entities into domain models that can be used in the UI or business logic.
 *
 * @return [Activity] The domain model with data from the [DbActivity].
 */
fun DbActivity.asDomainActivity() = Activity(
    id = id,
    title = title,
    starttime = starttime,
    endtime = endtime,
    description = description,
    maxregistrations = maxregistrations,
    amount = amount
)

/**
 * Extension function on a list of [DbActivity] entities to convert them into a list of domain model [Activity].
 * It uses the [asDomainActivity()] function defined above on each entity in the list.
 *
 * @return List<[Activity]> A list of domain model activities.
 */
fun List<DbActivity>.asDomainActivities() = map { it.asDomainActivity() }
