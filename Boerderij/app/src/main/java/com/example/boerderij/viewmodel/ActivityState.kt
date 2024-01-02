package com.example.boerderij.viewmodel

import com.example.boerderij.model.activity.Activity

/**
 * Data class representing the state related to activities in a ViewModel.
 *
 * @property currentActivityList The current list of activities.
 */
data class ActivityState(
    val currentActivityList: List<Activity>,
)
