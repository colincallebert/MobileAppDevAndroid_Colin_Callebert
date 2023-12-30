package com.example.boerderij.network.activityApi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiActivityResponse(
    @Json(name = "items")
    val items: List<Activity>,
    val count: Int
)

@JsonClass(generateAdapter = true)
data class Activity(
    val id: Int,
    val title: String,
    @Json(name = "starttime")
    val startTime: String,
    @Json(name = "endtime")
    val endTime: String,
    val description: String,
    val maxregistrations: Int,
    val amount: Int
)

