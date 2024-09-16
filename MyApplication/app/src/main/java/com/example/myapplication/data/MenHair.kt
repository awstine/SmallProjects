package com.example.myapplication.data

import com.example.myapplication.R

data class MenHair(
    val img: Int,
    val hairName: String,
    val hairPrice: Double,
)

val menHair =
    listOf(
        MenHair(R.drawable.img, "Hair Style 1", 21.0),
        MenHair(R.drawable.img_7, "Hair Style 2", 51.0),
        MenHair(R.drawable.img_11, "Hair Style 3", 78.0),
        MenHair(R.drawable.img_5, "Hair Style 4", 93.0),
        MenHair(R.drawable.img_9, "Hair Style 5", 27.0),
        MenHair(R.drawable.img_10, "Hair Style 6", 39.0),
    )
