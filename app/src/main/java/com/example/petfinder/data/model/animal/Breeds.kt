package com.example.petfinder.data.model.animal

import java.io.Serializable

data class Breeds(
    val mixed: Boolean?,
    val primary: String?,
    val secondary: String?,
    val unknown: Boolean?
): Serializable