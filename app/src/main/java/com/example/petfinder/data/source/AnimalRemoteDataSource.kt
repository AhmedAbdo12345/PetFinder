package com.example.petfinder.data.source

import com.example.petfinder.data.model.AnimalsResponse

interface AnimalRemoteDataSource {

    suspend fun getAllAnimals(): AnimalsResponse
}