package com.example.boerderij.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: DbActivity)

    @Query("SELECT * FROM DbActivity")
    fun getActivities(): Flow<List<DbActivity>>
}