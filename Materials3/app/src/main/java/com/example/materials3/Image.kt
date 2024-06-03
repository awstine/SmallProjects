package com.example.materials3

data class ImageData(
    val imageResource: Int,
    val contentDescription:String? = null
)

val imageList = listOf(
    ImageData(R.drawable.img),
    ImageData(R.drawable.img_1),
    ImageData(R.drawable.img_2),
    ImageData(R.drawable.img_3),
    ImageData(R.drawable.img_4),
    ImageData(R.drawable.img_5),
    ImageData(R.drawable.img_6),
    ImageData(R.drawable.img_7),
    ImageData(R.drawable.img_8),
    ImageData(R.drawable.img_9)
)