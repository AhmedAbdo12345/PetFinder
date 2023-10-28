package com.example.petfinder.data.source

import android.util.Log
import com.example.petfinder.data.model.token.AccessTokenResponse
import com.example.petfinder.data.model.animal.AnimalsResponse
import com.example.petfinder.data.model.types.TypeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IRemoteDataSourceImpl(private val apiService: ApiService, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : IRemoteDataSource {

    override suspend fun getToken(): AccessTokenResponse {
        return withContext(ioDispatcher){
            apiService.getAccessToken("client_credentials","Vx9UJgGvTkHK9K0YtEcqQbpbsjlzePGyrbu3dk0TVTYHVc59Vf","dOSATgMmqiaWyMCzhCmhBasZJSQgMPtXiSKXfBSi")
        }
    }
    override suspend fun getAllAnimals(accessToken: String): AnimalsResponse {
       return withContext(ioDispatcher){
         //  Log.d("zxcv", "getAllAnimals:  "+accessToken+"    cccc")

           apiService.getAnimals("Bearer "+accessToken)

       }
    }

    override suspend fun getTypes(accessToken: String): TypeResponse {
        return withContext(ioDispatcher){

       //     Log.d("zxcv", "getTypes:        "+accessToken+"    cccc")

            apiService.getTypes("Bearer "+accessToken)
        }
    }

    override suspend fun getFilterAnimal( accessToken: String,type:String): AnimalsResponse {
        return withContext(ioDispatcher){
       //     Log.d("zxcv", "getFilterAnimal: "+accessToken+"    cccc")

            apiService.getFilterAnimal("Bearer "+accessToken,type)
        }
    }


}