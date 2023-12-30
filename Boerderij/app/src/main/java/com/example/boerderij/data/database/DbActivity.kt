package com.example.boerderij.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boerderij.model.activity.Activity

@Entity
data class DbActivity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val starttime: String,
    val endtime: String,
    val description: String,
    val maxregistrations: Int,
    var amount: Int
)

fun Activity.asDbActivity() = DbActivity(
    id = id,
    title = title,
    starttime = starttime,
    endtime = endtime,
    description = description,
    maxregistrations = maxregistrations,
    amount = amount
)

fun DbActivity.asDomainActivity() = Activity(
    id = id,
    title = title,
    starttime = starttime,
    endtime = endtime,
    description = description,
    maxregistrations = maxregistrations,
    amount = amount
)

fun List<DbActivity>.asDomainActivities() = map { it.asDomainActivity() }

