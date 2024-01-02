package com.example.boerderij.data

import com.example.boerderij.data.database.ActivityDao
import com.example.boerderij.data.database.asDbActivity
import com.example.boerderij.data.database.asDomainActivities
import com.example.boerderij.model.activity.ActivityDetail
import com.example.boerderij.model.registration.Registration
import com.example.boerderij.network.ApiService
import com.example.boerderij.network.activityApi.Activity
import com.example.boerderij.network.activityApi.asDomainObject
import com.example.boerderij.network.activityApi.asDomainObjects
import com.example.boerderij.network.getActivitiesAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * This interface represents the repository layer for the application, responsible for data operations involving Conditions,
 * Doctors, and NewsArticles. It provides methods to get lists of these entities, get detail for a specific entity, insert new entities,
 * and refresh the local data with updates from a remote source.
 */
interface AppRepository {
    fun getActivities(): Flow<List<Activity>>
    suspend fun insertActivity(activity: Activity)
    suspend fun refreshActivities()
    suspend fun createRegistration(registration: Registration): Registration
    suspend fun deleteRegistration(id: Int): Registration
}

/**
 * This class is a concrete implementation of AppRepository that utilizes a caching strategy. It uses an API service to fetch
 * data from a remote source and local DAOs to cache data in a database. It provides the application with a consistent data view,
 * refreshing the cache as needed and serving data from the cache when available.
 *
 * @param apiService The API service to use for fetching data from a remote source.
 * @param conditionDao The DAO to use for caching Condition data.
 * @param doctorDao The DAO to use for caching Doctor data.
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
     * @return A Flow of List of Activity objects.
     */
    override suspend fun insertActivity(activity: Activity) {
        activityDao.insertActivity(activity.asDbActivity())
    }

    override suspend fun createRegistration(registration: Registration): Registration {
        return apiService.createRegistration(registration)
    }

    override suspend fun deleteRegistration(id: Int): Registration {
        return apiService.deleteRegistration(id)
    }


    /**
     * Refreshes the cache with the latest activities from the network.
     *
     * @return A Flow of List of Activity objects.
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