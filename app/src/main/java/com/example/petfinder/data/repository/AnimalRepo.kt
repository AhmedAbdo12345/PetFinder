package com.example.petfinder.data.repository

import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse
import kotlinx.coroutines.flow.Flow

interface AnimalRepo {
    suspend fun getAnimals(): Flow<AnimalsResponse>
    suspend fun getAccessToken(): AccessTokenResponse
}