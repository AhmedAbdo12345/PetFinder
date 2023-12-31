package com.example.petfinder.data.repository

import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse
import com.example.petfinder.data.remote.IRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnimalRepoImpl(private val iRemoteDataSource: IRemoteDataSource) : AnimalRepo {
    override suspend fun getAnimals(page: Int): Flow<AnimalsResponse> = flow {
        emit(iRemoteDataSource.getAllAnimals(page))
    }

    override suspend fun getTypes(): Flow<TypeResponse> = flow {
        emit(iRemoteDataSource.getTypes())
    }

    override suspend fun getAnimalForType(typeAnimal: String): Flow<AnimalsResponse> = flow {
        emit(iRemoteDataSource.getFilterAnimal(typeAnimal))
    }


}