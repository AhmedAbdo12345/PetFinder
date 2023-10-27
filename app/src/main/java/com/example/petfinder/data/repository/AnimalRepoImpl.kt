package com.example.petfinder.data.repository

import com.example.petfinder.data.model.AnimalsResponse
import com.example.petfinder.data.source.AnimalRemoteDataSource

class AnimalRepoImpl(private val animalRemoteDataSource: AnimalRemoteDataSource) : AnimalRepo {
    override suspend fun getAnimals(): AnimalsResponse {
        return animalRemoteDataSource.getAllAnimals()
    }
}