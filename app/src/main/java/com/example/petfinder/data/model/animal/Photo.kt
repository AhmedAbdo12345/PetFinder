package com.example.petfinder.data.model.animal

import java.io.Serializable

data class Photo(
    val full: String?,
    val large: String?,
    val medium: String?,
    val small: String?
): Serializable