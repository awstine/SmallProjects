package com.example.myapplication.data2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavouriteItem(
    @PrimaryKey
    val id: Int,
    val hairName: String,
    val img: Int,
)
