package com.example.petfinder.data.source

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.petfinder.com/v2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}