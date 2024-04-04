package com.example.broadcastrcvtesting.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator  {
    private const val BASE_URL = "https://api.github.com/"
    private const val BASE_REPO_URL = ""
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: APIInterface by lazy {
        APIInterface.retrofitService(retrofit)
    }
}