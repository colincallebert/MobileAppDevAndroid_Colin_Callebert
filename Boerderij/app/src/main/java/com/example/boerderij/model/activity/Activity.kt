package com.example.boerderij.model.activity
import kotlinx.serialization.Serializable

@Serializable
data class ApiActivityResponse(
    val items: List<Activity>,
    val count: Int
)

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