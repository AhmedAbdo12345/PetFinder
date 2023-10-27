package com.example.petfinder.data.repository

import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.source.AnimalRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnimalRepoImpl(private val animalRemoteDataSource: AnimalRemoteDataSource) : AnimalRepo {
    override suspend fun getAnimals(): Flow<AnimalsResponse> = flow {
        emit(animalRemoteDataSource.getAllAnimals())
    }

    override suspend fun getAccessToken(): AccessTokenResponse {
        return animalRemoteDataSource.getToken()
    }

}