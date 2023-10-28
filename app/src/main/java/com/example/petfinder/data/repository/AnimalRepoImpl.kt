package com.example.petfinder.data.repository

import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse
import com.example.petfinder.data.source.IRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnimalRepoImpl(private val iRemoteDataSource: IRemoteDataSource) : AnimalRepo {
    override suspend fun getAnimals(): Flow<AnimalsResponse> = flow {
        emit(iRemoteDataSource.getAllAnimals())
    }

    override suspend fun getTypes(): Flow<TypeResponse> = flow {
        emit(iRemoteDataSource.getTypes())
    }

    override suspend fun getAccessToken(): AccessTokenResponse {
        return iRemoteDataSource.getToken()
    }

}