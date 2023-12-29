package com.example.boerderij.data

import com.example.boerderij.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:9000/api/"

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(Json { ignoreUnknownKeys = true }
                .asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()


        retrofit.create(ApiService::class.java)
    }
}

