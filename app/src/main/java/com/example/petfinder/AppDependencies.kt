package com.example.petfinder

import com.example.petfinder.data.repository.AnimalRepoImpl
import com.example.petfinder.data.source.IRemoteDataSourceImpl
import com.example.petfinder.data.source.ApiService
import com.example.petfinder.data.source.RetrofitInstance

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