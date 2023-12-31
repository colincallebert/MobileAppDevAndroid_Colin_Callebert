package com.example.boerderij.model.activity

import kotlinx.serialization.Serializable

@Serializable
data class ActivityDetail(
    val id: Int,
    val title: String,
    val starttime: String,
    val endtime: String,
    val description: String,
    val maxregistrations: Int,
    val amount: Int
)
