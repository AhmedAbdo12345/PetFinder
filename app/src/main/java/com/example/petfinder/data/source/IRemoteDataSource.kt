package com.example.petfinder.data.source

import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse

interface IRemoteDataSource {

    suspend fun getToken(): AccessTokenResponse
    suspend fun getAllAnimals(): AnimalsResponse

    suspend fun getTypes(): TypeResponse

    suspend fun getFilterAnimal(type: String): AnimalsResponse

}