package com.example.petfinder.data.source

import android.util.Log
import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IRemoteDataSourceImpl(private val apiService: ApiService, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : IRemoteDataSource {
    override suspend fun getAllAnimals(): AnimalsResponse {
       return withContext(ioDispatcher){
           Log.d("zxcv", "getAllAnimals: "+getToken().accessToken)
           apiService.getAnimals("Bearer "+getToken().accessToken)

       }
    }

    override suspend fun getToken(): AccessTokenResponse {
      return withContext(ioDispatcher){
          apiService.getAccessToken("client_credentials","Vx9UJgGvTkHK9K0YtEcqQbpbsjlzePGyrbu3dk0TVTYHVc59Vf","dOSATgMmqiaWyMCzhCmhBasZJSQgMPtXiSKXfBSi")
      }
    }
}