package com.example.petfinder.data.repository

import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse
import com.example.petfinder.data.source.IRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnimalRepoImpl(private val iRemoteDataSource: IRemoteDataSource) : AnimalRepo {
    override suspend fun getAnimals(accessToken: String): Flow<AnimalsResponse> = flow {
        emit(iRemoteDataSource.getAllAnimals(accessToken))
    }

    override suspend fun getTypes(accessToken: String): Flow<TypeResponse> = flow {
        emit(iRemoteDataSource.getTypes(accessToken))
    }

    override suspend fun getAnimalForType(accessToken: String, typeAnimal: String): Flow<AnimalsResponse> = flow {
        emit(iRemoteDataSource.getFilterAnimal(accessToken,typeAnimal))
    }

    override suspend fun getAccessToken(): AccessTokenResponse {

        return iRemoteDataSource.getToken()
    }

}