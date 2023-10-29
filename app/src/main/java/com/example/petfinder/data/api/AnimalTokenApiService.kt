package com.example.petfinder.data.api

import com.example.petfinder.data.model.token.AccessTokenResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AnimalTokenApiService {

    @FormUrlEncoded
    @POST("oauth2/token")
    fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Call<AccessTokenResponse>

}