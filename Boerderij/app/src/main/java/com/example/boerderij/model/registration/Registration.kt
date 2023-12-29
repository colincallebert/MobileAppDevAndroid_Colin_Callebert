package com.example.boerderij.model.registration

import kotlinx.serialization.Serializable

@Serializable
data class Registration(
    val userid: Int,
    val activityid: Int,
    var amount: Int
)