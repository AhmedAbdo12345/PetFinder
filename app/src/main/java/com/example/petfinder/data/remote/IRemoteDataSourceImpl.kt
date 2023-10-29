package com.example.petfinder.data.remote

import com.example.petfinder.data.api.ApiService
import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IRemoteDataSourceImpl(private val apiService: ApiService, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : IRemoteDataSource {

    override suspend fun getAllAnimals(page:Int): AnimalsResponse {
       return withContext(ioDispatcher){

           apiService.getAnimals(page)
       }
    }

    override suspend fun getTypes(): TypeResponse {
        return withContext(ioDispatcher){

            apiService.getTypes()
        }
    }

    override suspend fun getFilterAnimal(type:String): AnimalsResponse {
        return withContext(ioDispatcher){

            apiService.getFilterAnimal(type)
        }
    }


}