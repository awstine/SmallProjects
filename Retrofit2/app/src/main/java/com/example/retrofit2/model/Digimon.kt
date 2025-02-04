package com.example.retrofit2.model

import com.google.gson.annotations.SerializedName

data class Digimon(
    val name: String,
    @SerializedName("img") val imageUrl: String,
    val level: String
)
