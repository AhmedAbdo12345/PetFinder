package com.example.petfinder.data.source

import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse

interface IRemoteDataSource {

    suspend fun getAllAnimals(): AnimalsResponse
    suspend fun getToken(): AccessTokenResponse
}