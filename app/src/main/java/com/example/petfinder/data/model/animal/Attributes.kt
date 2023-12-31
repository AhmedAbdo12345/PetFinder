package com.example.petfinder.data.model.animal

import java.io.Serializable

data class Attributes(
    val declawed: Boolean?,
    val house_trained: Boolean?,
    val shots_current: Boolean?,
    val spayed_neutered: Boolean?,
    val special_needs: Boolean?
): Serializable