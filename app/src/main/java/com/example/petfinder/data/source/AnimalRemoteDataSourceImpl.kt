package com.example.petfinder.data.source

import com.example.petfinder.data.model.AnimalsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimalRemoteDataSourceImpl(private val apiService: ApiService, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : AnimalRemoteDataSource {
    override suspend fun getAllAnimals(): AnimalsResponse {
       return withContext(ioDispatcher){
           apiService.getAnimals()
       }
    }
}