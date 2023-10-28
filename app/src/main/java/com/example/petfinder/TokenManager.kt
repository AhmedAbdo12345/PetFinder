package com.example.petfinder

import android.util.Log
import com.example.petfinder.data.repository.AnimalRepo
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object TokenManager {
    @Volatile
    private var token: String? = null
    private var tokenExpirationTime: Long = 0
    private val  mutex = Mutex()

    suspend fun getToken( animalRepo: AnimalRepo): String? {
        mutex.withLock {
            if (token == null || System.currentTimeMillis() >= tokenExpirationTime) {
                // Token is expired or not available, obtain a new one
                val response = animalRepo.getAccessToken()
                response.let {

                    token = it.accessToken
                    tokenExpirationTime = System.currentTimeMillis() + (it.expiresIn ?: 0) * 1000

                }
            }
        }
            return token
        }

}