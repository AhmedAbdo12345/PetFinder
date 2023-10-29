package com.example.petfinder.di

import android.content.Context
import com.example.petfinder.data.repository.AnimalRepoImpl
import com.example.petfinder.data.remote.IRemoteDataSourceImpl
import com.example.petfinder.data.api.ApiService
import com.example.petfinder.data.api.RetrofitInstance

class AppDependencies(private val context: Context) {

    val animalRepo by lazy {

        val apiService : ApiService = RetrofitInstance(context.getSharedPreferences("App",Context.MODE_PRIVATE)).api
        val iRemoteDataSource = IRemoteDataSourceImpl(apiService)
        AnimalRepoImpl(iRemoteDataSource)
    }
}