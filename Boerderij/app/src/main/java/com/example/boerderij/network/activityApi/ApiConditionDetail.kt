package com.example.boerderij.network.activityApi

import kotlinx.serialization.Serializable

@Serializable
data class ApiActivityResponseDetail(
    val activity: ActivityDetail
)

@Serializable
data class ActivityDetail(
    val id: Int,
    val title: String,
    val starttme: String,
    val endtime: String,
    val description: String,
    val maxregistrations: Int,
    var amount: Int
)

fun ApiActivityResponseDetail.asDomainObject(): ActivityDetail {
    return this.activity.let {
        ActivityDetail(
            id = it.id,
            title = it.title,
            starttme = it.starttme,
            endtime = it.endtime,
            description = it.description,
            maxregistrations = it.maxregistrations,
            amount = it.amount
        )
    }
}
