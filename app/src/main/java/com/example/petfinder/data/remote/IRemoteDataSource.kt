package com.example.petfinder.data.remote

import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse

interface IRemoteDataSource {

    suspend fun getAllAnimals(page:Int): AnimalsResponse

    suspend fun getTypes(): TypeResponse

    suspend fun getFilterAnimal(type:String): AnimalsResponse

}