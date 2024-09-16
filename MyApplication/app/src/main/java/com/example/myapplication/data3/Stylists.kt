package com.example.myapplication.data3

import com.example.myapplication.R

data class Stylists(
    val img: Int,
    val firstName: String,
    val lastName: String,
    val state: String,
)

val stylistList =
    listOf(
        Stylists(R.drawable.img_12, "John", "Doe", "Engaged"),
        Stylists(R.drawable.img_16, "Jane", "Harris", "Engaged"),
        Stylists(R.drawable.img_13, "James", "Smith", "Available"),
        Stylists(R.drawable.img_17, "Dorris", "Imelda", "Engaged"),
        Stylists(R.drawable.img_14, "Dan", "Johnson", "Available"),
        Stylists(R.drawable.img_18, "Mitchell", "Rose", "Available"),
        Stylists(R.drawable.img_15, "Bill", "Williams", "Available"),
        Stylists(R.drawable.img_19, "Tracy", "Kaliesa", "Engaged"),
    )
