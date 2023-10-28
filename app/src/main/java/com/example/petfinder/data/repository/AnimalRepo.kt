package com.example.petfinder.data.repository

import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse
import kotlinx.coroutines.flow.Flow

interface AnimalRepo {
    suspend fun getAccessToken(): AccessTokenResponse
    suspend fun getAnimals(accessToken: String): Flow<AnimalsResponse>

    suspend fun getTypes(accessToken: String): Flow<TypeResponse>

    suspend fun getAnimalForType(accessToken: String,typeAnimal: String): Flow<AnimalsResponse>
}