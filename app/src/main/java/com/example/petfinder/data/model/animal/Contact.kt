package com.example.petfinder.data.model.animal

import java.io.Serializable

data class Contact(
    val address: Address?,
    val email: String?,
    val phone: String?
): Serializable