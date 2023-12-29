package com.example.boerderij.model.activity

import kotlinx.serialization.Serializable

@Serializable
data class ActivtyDetail(
    val id: Int,
    val title: String,
    val starttme: String,
    val endtime: String,
    val description: String,
    val maxregistrations: Int,
    var amount: Int
)