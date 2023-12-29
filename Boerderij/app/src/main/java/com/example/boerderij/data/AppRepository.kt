//package com.example.boerderij.data
//
//import com.example.boerderij.data.database.ActivityDao
//import com.example.boerderij.data.database.DbActivity
//import com.example.boerderij.data.database.asDomainActivities
//import com.example.boerderij.model.activity.Activity
//import com.example.boerderij.network.ApiService
//import com.example.boerderij.network.activityApi.ActivityDetail
//import com.example.boerderij.network.activityApi.asDomainObject
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//interface AppRepository {
//
//    fun getActivities(): Flow<List<Activity>>
//
//    suspend fun getActivityDetailById(id: Int): ActivityDetail?
//}
//class CachingAppRepository(
//    private val apiService: ApiService,
//    private val activityDao: ActivityDao,
//
//    ) : AppRepository {
//        override fun getActivities(): Flow<List<Activity>> {
//            return activityDao.getActivities().map { it.asDomainActivities() }
//        }
//        override suspend fun getActivityDetailById(id: Int): ActivityDetail? {
//            return apiService.getActivityDetailById(id).asDomainObject()
//        }
//    }
