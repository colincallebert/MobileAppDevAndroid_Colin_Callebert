package com.example.boerderij.network

import com.example.boerderij.model.registration.Registration
import com.example.boerderij.network.activityApi.ApiActivityResponse
import kotlinx.coroutines.flow.flow
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Defines the network API service for fetching activities, creating registrations, and deleting registrations from a REST API.
 * It uses Retrofit annotations to indicate API endpoints and parameters.
 */
interface ApiService {
    /**
     * Fetches all activities from the server.
     *
     * @return [ApiActivityResponse] The response containing a list of activities.
     */
    @GET("activities")
    suspend fun getActivities(): ApiActivityResponse

    /**
     * Sends a POST request to create a new registration on the server.
     *
     * @param registration The registration data to be created.
     * @return [Registration] The created registration object.
     */
    @POST("registrations")
    suspend fun createRegistration(@Body registration: Registration): Registration

    /**
     * Sends a DELETE request to remove a registration with the specified ID.
     *
     * @param id The ID of the registration to be deleted.
     * @return [Registration] The deleted registration object.
     */
    @DELETE("registrations/{id}")
    suspend fun deleteRegistration(@Path("id") id: Int): Registration
}

/**
 * Extension function for [ApiService] to get activities as a Kotlin Flow.
 *
 * @return [Flow]<[ApiActivityResponse]> A flow emitting the activity response.
 */
fun ApiService.getActivitiesAsFlow() = flow {
    emit(getActivities())
}
