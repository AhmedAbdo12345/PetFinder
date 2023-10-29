package com.example.petfinder.data.model.animal

import java.io.Serializable

data class Environment(
    val cats: Boolean?,
    val children: Boolean?,
    val dogs: Boolean?
): Serializable