package com.example.myapplication.data

import com.example.myapplication.R

data class Hair(
    val img: Int,
    val hairName: String,
    val hairPrice: Double,
)

val hairList =
    listOf(
        Hair(R.drawable.img, "Hair Style 1", 10.0),
        Hair(R.drawable.img_1, "Hair Style 2", 20.0),
        Hair(R.drawable.img_2, "Hair Style 3", 30.0),
        Hair(R.drawable.img_3, "Hair Style 4", 40.0),
        Hair(R.drawable.img_4, "Hair Style 5", 50.0),
        Hair(R.drawable.img_5, "Hair Style 6", 60.0),
        Hair(R.drawable.img_6, "Hair Style 7", 70.0),
        Hair(R.drawable.img_7, "Hair Style 8", 80.0),
        Hair(R.drawable.img_8, "Hair Style 9", 90.0),
    )
