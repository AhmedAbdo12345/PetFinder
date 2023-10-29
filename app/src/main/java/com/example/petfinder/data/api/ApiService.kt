package com.example.petfinder.data.api

import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("animals")
    suspend fun getAnimals(@Query("page") page: Int): AnimalsResponse

    @GET("types/")
    suspend fun getTypes(): TypeResponse

    @GET("animals")
    suspend fun getFilterAnimal(@Query("type") types: String): AnimalsResponse


}