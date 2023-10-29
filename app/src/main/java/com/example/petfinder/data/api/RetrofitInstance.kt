package com.example.petfinder.data.api

import android.content.SharedPreferences
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class AnimalAuthenticator(
    private val shardPreferences: SharedPreferences,
    private val tokenApiService: AnimalTokenApiService
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {

       val responseToken =  tokenApiService.getAccessToken(
            "client_credentials",
            "Vx9UJgGvTkHK9K0YtEcqQbpbsjlzePGyrbu3dk0TVTYHVc59Vf",
            "dOSATgMmqiaWyMCzhCmhBasZJSQgMPtXiSKXfBSi"
        ).execute()
        shardPreferences.edit().putString("accessToken", "Bearer "+responseToken.body()?.accessToken).commit()

        return response.request
    }

}

class AnimalInterceptor(private val shardPreferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().addHeader(
            "Authorization", shardPreferences.getString(
                "accessToken",
                ""
            ) ?: ""
        ).build()
        return chain.proceed(newRequest)
    }

}

class RetrofitInstance(private val shardPreferences: SharedPreferences) {

    private val tokenApiService = Retrofit.Builder()
        .baseUrl("https://api.petfinder.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<AnimalTokenApiService>()

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    val client = OkHttpClient.Builder()
        .authenticator(AnimalAuthenticator(shardPreferences,tokenApiService)).addNetworkInterceptor(AnimalInterceptor(shardPreferences))
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.petfinder.com/v2/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val api: ApiService by  lazy {
        retrofit.create(ApiService::class.java)
    }
}