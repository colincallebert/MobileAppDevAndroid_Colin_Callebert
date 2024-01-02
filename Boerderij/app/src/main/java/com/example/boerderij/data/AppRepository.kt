package com.example.boerderij.data

import com.example.boerderij.data.database.ActivityDao
import com.example.boerderij.data.database.asDbActivity
import com.example.boerderij.data.database.asDomainActivities
import com.example.boerderij.model.registration.Registration
import com.example.boerderij.network.ApiService
import com.example.boerderij.network.activityApi.Activity
import com.example.boerderij.network.activityApi.asDomainObjects
import com.example.boerderij.network.getActivitiesAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * Repository interface responsible for data operations involving activities and registrations.
 * It provides methods to get lists of activities, get details for a specific activity,
 * insert new activities, and refresh the local data with updates from a remote source.
 */
interface AppRepository {
    /**
     * Retrieves a list of activities from the cache, refreshing from the network if the cache is empty.
     *
     * @return A Flow of List of Activity objects.
     */
    fun getActivities(): Flow<List<Activity>>

    /**
     * Inserts a new activity into the cache.
     *
     * @param activity The activity to be inserted.
     */
    suspend fun insertActivity(activity: Activity)

    /**
     * Refreshes the cache with the latest activities from the network.
     */
    suspend fun refreshActivities()

    /**
     * Creates a new registration by sending the registration data to the remote API.
     *
     * @param registration The registration data to be sent.
     * @return The created Registration object.
     */
    suspend fun createRegistration(registration: Registration): Registration

    /**
     * Deletes a registration by sending a request to the remote API.
     *
     * @param id The ID of the registration to be deleted.
     * @return The deleted Registration object.
     */
    suspend fun deleteRegistration(id: Int): Registration
}

/**
 * Implementation of the [AppRepository] interface that includes caching functionality.
 *
 * @param apiService The [ApiService] instance used for network operations.
 * @param activityDao The [ActivityDao] instance used for database operations.
 */
class CachingAppRepository(
    private val apiService: ApiService,
    private val activityDao: ActivityDao,
) : AppRepository {
    /**
     * Retrieves a list of activities from the cache, refreshing from the network if the cache is empty.
     *
     * @return A Flow of List of Activity objects.
     */
    override fun getActivities(): Flow<List<Activity>> {
        return activityDao.getAllActivities().map {
            it.asDomainActivities()
        }.onEach {
            if (it.isEmpty()) {
                refreshActivities()
            }
        }
    }

    /**
     * Inserts a new activity into the cache.
     *
     * @param activity The activity to be inserted.
     */
    override suspend fun insertActivity(activity: Activity) {
        activityDao.insertActivity(activity.asDbActivity())
    }

    /**
     * Creates a new registration by sending the registration data to the remote API.
     *
     * @param registration The registration data to be sent.
     * @return The created Registration object.
     */
    override suspend fun createRegistration(registration: Registration): Registration {
        return apiService.createRegistration(registration)
    }

    /**
     * Deletes a registration by sending a request to the remote API.
     *
     * @param id The ID of the registration to be deleted.
     * @return The deleted Registration object.
     */
    override suspend fun deleteRegistration(id: Int): Registration {
        return apiService.deleteRegistration(id)
    }

    /**
     * Refreshes the cache with the latest activities from the network.
     */
    override suspend fun refreshActivities() {
        try {
            apiService.getActivitiesAsFlow().asDomainObjects().collect {
                for (activity in it) {
                    insertActivity(activity)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
