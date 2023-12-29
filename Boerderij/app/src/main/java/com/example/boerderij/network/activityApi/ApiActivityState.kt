package com.example.boerderij.network.activityApi

import com.example.boerderij.model.activity.Activity
import com.example.boerderij.model.activity.ActivtyDetail

sealed interface ActivityApiState {

    object Loading : ActivityApiState

    object Error : ActivityApiState

    object Success : ActivityApiState
}

sealed interface ActivityDetailApiState {

    object Loading : ActivityDetailApiState

    data class Success(val activity: ActivtyDetail?) : ActivityDetailApiState

    object Error : ActivityDetailApiState
}


data class ConditionListState(val activityList: List<Activity> = listOf())
