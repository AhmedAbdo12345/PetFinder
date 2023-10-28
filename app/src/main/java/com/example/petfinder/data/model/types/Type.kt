package com.example.petfinder.data.model.types

data class Type(
    val _links: Links?,
    val coats: List<String>?,
    val colors: List<String>?,
    val genders: List<String>?,
    val name: String?
)