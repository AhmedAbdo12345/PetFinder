package com.example.petfinder.data.model

data class AnimalsResponse(
    val animals: List<Animal>,
    val pagination: Pagination
)