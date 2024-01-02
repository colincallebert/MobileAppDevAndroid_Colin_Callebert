package com.example.boerderij.data

import com.example.boerderij.model.registration.Registration
import com.example.boerderij.network.activityApi.Activity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeApiAppRepository : AppRepository {
    override fun getActivities(): Flow<List<Activity>> {
        return flowOf(FakeDataSource.activities.toMutableList())
    }

    override suspend fun insertActivity(activity: Activity) {
        FakeDataSource.activities.toMutableList().add(activity)
    }

    override suspend fun refreshActivities() {
        FakeDataSource.activities.toMutableList().clear()
    }

    override suspend fun createRegistration(registration: Registration): Registration {
        return registration
    }

    override suspend fun deleteRegistration(id: Int): Registration {
        return Registration(userid = 1, activityid = id, amount = 2)
    }
}
