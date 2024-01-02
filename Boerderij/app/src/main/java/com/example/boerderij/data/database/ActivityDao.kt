package com.example.boerderij.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing CRUD operations for activities in the database.
 * It provides methods to insert activities and retrieve all activities.
 * This interface is used by Room to generate code that interacts with the database.
 */
@Dao
interface ActivityDao {
    /**
     * Inserts an activity into the database. If an activity with the same ID already exists,
     * it replaces the old activity with the new one.
     * This is a suspend function and should be called from a coroutine or another suspend function.
     *
     * @param activity The activity entity to be inserted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: DbActivity)

    /**
     * Retrieves all activities from the database as a list.
     * Returns a Flow that emits every time the data in the database changes.
     * This allows the UI to automatically update when the database data changes.
     *
     * @return Flow<List<DbActivity>> A flow emitting the list of activities.
     */
    @Query("SELECT * FROM DbActivity")
    fun getAllActivities(): Flow<List<DbActivity>>
}
