package com.example.petfinder.data.repository

import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse
import kotlinx.coroutines.flow.Flow

interface AnimalRepo {
    suspend fun getAnimals(page:Int): Flow<AnimalsResponse>

    suspend fun getTypes(): Flow<TypeResponse>

    suspend fun getAnimalForType(typeAnimal: String): Flow<AnimalsResponse>
}