package com.example.boerderij.network

import com.example.boerderij.model.activity.Activity
import com.example.boerderij.model.activity.ApiActivityResponse
import com.example.boerderij.model.registration.Registration
import retrofit2.http.Body
import retrofit2.http.DELETE

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("activities")
    suspend fun getActivities(): ApiActivityResponse

    @GET("activities/{id}")
    suspend fun getActivityDetailById(@Path("id") id: Int): Activity

    @POST("registrations")
    suspend fun createRegistration(@Body registration: Registration): Registration

    @DELETE("registrations/{id}")
    suspend fun deleteRegistration(@Path("id") id: Int): Registration
}