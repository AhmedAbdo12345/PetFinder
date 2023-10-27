package com.example.petfinder.data.repository

import com.example.petfinder.data.model.AnimalsResponse

interface AnimalRepo {
    suspend fun getAnimals(): AnimalsResponse
}