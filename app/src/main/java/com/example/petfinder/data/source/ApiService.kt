package com.example.petfinder.data.source

import com.example.petfinder.data.model.AnimalsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


data class TokenReqBody(
    val grant_type: String,
    val client_id: String,
    val client_secret: String
)

interface ApiService {

    @POST("oauth2/token")
    fun getToken(@Body body: TokenReqBody): String

    @GET("animals")
    fun getAnimals(): AnimalsResponse
}