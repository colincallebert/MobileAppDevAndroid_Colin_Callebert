package com.example.boerderij.data

import android.content.Context
import com.example.boerderij.data.database.AppDatabase
import com.example.boerderij.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Interface defining the dependency container for the application.
 */
interface AppContainer {
    val appRepository: AppRepository
}

/**
 * Default implementation of [AppContainer] providing instances of dependencies.
 *
 * @param context The application context.
 */
class DefaultAppContainer(
    private val context: Context
) : AppContainer {
    // Base URL for the API.
    private val BASE_URL = "http://10.0.2.2:9000/api/"

    // Configures Retrofit for networking, using Kotlin serialization for JSON processing.
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json { ignoreUnknownKeys = true }
            .asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    // Lazily initializes the ApiService using Retrofit.
    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    // Provides a single instance of AppRepository throughout the app lifecycle.
    override val appRepository: AppRepository by lazy {
        CachingAppRepository(
            apiService,
            AppDatabase.getDatabase(context = context).activityDao(),
        )
    }
}
