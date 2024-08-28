package com.example.jobhuntapp.clients

import com.example.jobhuntapp.api.MainAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://your.api.url/"

    val instance: MainAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainAPI::class.java)
    }
}