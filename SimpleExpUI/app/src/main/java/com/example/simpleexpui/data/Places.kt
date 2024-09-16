package com.example.simpleexpui.data

import com.example.simpleexpui.R


data class Place (
    val image: Int,
    val name: String,
    val address: String
)

val places = listOf(
    Place(
        R.drawable.img,
        "Directions",
        "Malasia"
    ),
    Place(
        R.drawable.img_1,
        "Sky Diving",
        "Tokyo"
    ),
    Place(
        R.drawable.img_2,
        "Mountain Climbing",
        "Nairobi"
    ),
    Place(
        R.drawable.img_3,
        "Walks",
        "China"
    ),
)