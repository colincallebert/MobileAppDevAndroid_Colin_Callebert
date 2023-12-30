package com.example.boerderij.modelview

import com.example.boerderij.data.RetrofitInstance
import com.example.boerderij.model.activity.Activity
import com.example.boerderij.model.registration.Registration

class ActivityController {

    suspend fun registreren(activityId: Int, amount: Int) {
        val registration = Registration(
            userid = 1,
            activityid = activityId,
            amount = amount
        )
        try {
            RetrofitInstance.apiService.createRegistration(registration)
        } catch (e: Exception) {
            //throw GeneralErrors.RegistrationError(e)
        }
    }

    suspend fun getActivities(): List<Activity> {
        try {
            return RetrofitInstance.apiService.getActivities().items
        } catch (e: Exception) {
            return emptyList()
            //throw GeneralErrors.ActivityError(e)
        }
    }

    suspend fun getActivityDetailById(id: Int): Activity {
        try {
            return RetrofitInstance.apiService.getActivityDetailById(id)
        } catch (e: Exception) {
            return Activity(
                id = 0,
                title = "",
                starttime = "",
                endtime = "",
                description = "",
                maxregistrations = 0,
                amount = 0
            )
            //throw GeneralErrors.ActivityError(e)
        }
    }

    suspend fun deleteRegistration(id: Int) {
        try {
            RetrofitInstance.apiService.deleteRegistration(id)
        } catch (e: Exception) {
            //throw GeneralErrors.RegistrationError(e)
        }
    }
}