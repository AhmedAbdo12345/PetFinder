package com.example.petfinder.data.model.animal

import java.io.Serializable

data class Links(
    val organization: Organization?,
    val self: Self?,
    val type: Type?
): Serializable