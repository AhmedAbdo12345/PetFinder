package com.example.petfinder.data.model.animal

data class AnimalsResponse(
    val animals: List<Animal>,
    val pagination: Pagination
)