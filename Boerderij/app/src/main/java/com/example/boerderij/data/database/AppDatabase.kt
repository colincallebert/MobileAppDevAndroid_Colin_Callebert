package com.example.boerderij.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room Database class for managing the persistence of activities.
 */
@Database(
    entities = [DbActivity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Abstract method to get the Data Access Object (DAO) for activities.
     *
     * @return The [ActivityDao] instance.
     */
    abstract fun activityDao(): ActivityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Gets the singleton instance of [AppDatabase]. If the instance does not exist,
         * it creates a new instance using Room's databaseBuilder.
         * It uses a synchronized block to ensure thread safety during instance creation.
         *
         * @param context The application context.
         * @return The singleton instance of [AppDatabase].
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration() // Handle migrations by destroying and rebuilding the database
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
