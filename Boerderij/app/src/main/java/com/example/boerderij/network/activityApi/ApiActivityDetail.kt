package com.example.boerderij.network.activityApi

import kotlinx.serialization.Serializable

data class ApiActivityResponseDetail(
    val activity: Activity
)

@Serializable
data class Detail(
    val id: Int,
    val title: String,
    val starttime: String,
    val endtime: String,
    val description: String,
    val maxregistrations: Int,
    val amount: Int
)

fun ApiActivityResponseDetail.asDomainObject(): Activity {
    return this.activity.let {
        Activity(
            id = it.id,
            title = it.title,
            starttime = it.starttime,
            endtime = it.endtime,
            description = it.description,
            maxregistrations = it.maxregistrations,
            amount = it.amount
        )
    }
}