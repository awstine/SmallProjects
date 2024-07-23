package com.example.myapplication.data

import com.example.myapplication.R

data class WomenHair(
    val img: Int,
    val hairName: String,
    val hairPrice: Double,
)

val womenHair =
    listOf(
        WomenHair(R.drawable.img_2, "Hair Style 1", 21.0),
        WomenHair(R.drawable.img_1, "Hair Style 2", 47.0),
        WomenHair(R.drawable.img_3, "Hair Style 3", 96.0),
        WomenHair(R.drawable.img_4, "Hair Style 4", 70.0),
        WomenHair(R.drawable.img_8, "Hair Style 4", 98.0),
    )
