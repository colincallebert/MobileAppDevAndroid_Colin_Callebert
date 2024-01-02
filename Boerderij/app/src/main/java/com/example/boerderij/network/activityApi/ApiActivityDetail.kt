package com.example.boerderij.network.activityApi

import com.example.boerderij.model.activity.ActivityDetail
import kotlinx.serialization.Serializable

data class ApiActivityResponseDetail(
    val activity: ActivityDetail
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

fun ApiActivityResponseDetail.asDomainObject(): ActivityDetail {
    return this.activity.let {
        ActivityDetail(
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