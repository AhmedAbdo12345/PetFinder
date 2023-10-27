package com.example.petfinder

import com.example.petfinder.data.repository.AnimalRepo
import com.example.petfinder.data.repository.AnimalRepoImpl
import com.example.petfinder.data.source.AnimalRemoteDataSource
import com.example.petfinder.data.source.AnimalRemoteDataSourceImpl
import com.example.petfinder.data.source.ApiService
import com.example.petfinder.data.source.RetrofitInstance

class AppDependencies {

    val animalRepo by lazy {

        val apiService : ApiService = RetrofitInstance.api
        val animalRemoteDataSource = AnimalRemoteDataSourceImpl(apiService)
        AnimalRepoImpl(animalRemoteDataSource)
    }
}