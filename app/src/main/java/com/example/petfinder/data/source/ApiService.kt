package com.example.petfinder.data.source

import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


/*data class TokenReqBody(
    val grant_type: String,
    val client_id: String,
    val client_secret: String
)
*/
interface ApiService {

    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): AccessTokenResponse

    @GET("animals")
   suspend fun getAnimals(@Header("Authorization") accessToken: String): AnimalsResponse


}