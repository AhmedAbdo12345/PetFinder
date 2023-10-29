package com.example.petfinder.di

import com.example.petfinder.data.repository.AnimalRepoImpl
import com.example.petfinder.data.remote.IRemoteDataSourceImpl
import com.example.petfinder.data.api.ApiService
import com.example.petfinder.data.api.RetrofitInstance

class AppDependencies {

    val animalRepo by lazy {

        val apiService : ApiService = RetrofitInstance.api
        val iRemoteDataSource = IRemoteDataSourceImpl(apiService)
        AnimalRepoImpl(iRemoteDataSource)
    }
   /* val tokenManager by lazy {
      //  TokenManager.getToken(animalRepo)
    }*/
}