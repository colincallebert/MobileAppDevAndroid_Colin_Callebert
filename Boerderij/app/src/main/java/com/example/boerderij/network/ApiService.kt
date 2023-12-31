package com.example.boerderij.network

import com.example.boerderij.model.registration.Registration
import com.example.boerderij.network.activityApi.ApiActivityResponse
import com.example.boerderij.network.activityApi.ApiActivityResponseDetail
import kotlinx.coroutines.flow.flow
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Defines the network API service for fetching news, doctors, and conditions from a REST API.
 * It uses Retrofit annotations to indicate API endpoints and parameters.
 */
interface ApiService {
    /**
     * Fetches all activities from the server.
     * @return ApiActivityResponse The response containing a list of activities.
     */
    @GET("activities")
    suspend fun getActivities(): ApiActivityResponse

    @GET("activities/{id}")
    suspend fun getActivityDetailById(@Path("id") id: Int): ApiActivityResponseDetail

    @POST("registrations")
    suspend fun createRegistration(@Body registration: Registration): Registration

    @DELETE("registrations/{id}")
    suspend fun deleteRegistration(@Path("id") id: Int): Registration

}


/**
 * Extension function for ApiService to get activities as a Kotlin Flow.
 * @return Flow<ApiActivitiesResponse> A flow emitting the activity response.
 */
fun ApiService.getActivitiesAsFlow() = flow {
    emit(getActivities())
}
