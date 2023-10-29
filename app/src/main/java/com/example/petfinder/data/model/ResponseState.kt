package com.example.petfinder.data.model

sealed class ResponseState<T> {
    data class OnSuccess<T>(var response: T) : ResponseState<T>()
    data class OnError<T>(var message: String) : ResponseState<T>()
     class OnLoading<T>() : ResponseState<T>()
}