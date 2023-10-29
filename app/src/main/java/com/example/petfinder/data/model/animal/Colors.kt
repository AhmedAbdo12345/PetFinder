package com.example.petfinder.data.model.animal

import java.io.Serializable

data class Colors(
    val primary: String?,
    val secondary: String?,
    val tertiary: Any?
): Serializable