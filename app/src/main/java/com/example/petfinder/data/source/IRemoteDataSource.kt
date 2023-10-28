package com.example.petfinder.data.source

import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse

interface IRemoteDataSource {

    suspend fun getToken(): AccessTokenResponse
    suspend fun getAllAnimals(accessToken: String): AnimalsResponse

    suspend fun getTypes(accessToken: String): TypeResponse

    suspend fun getFilterAnimal(accessToken: String,type:String): AnimalsResponse

}