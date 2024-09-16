package com.example.myapplication.data2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: FavouriteItem)

    @Delete
    suspend fun deleteFavourite(favourite: FavouriteItem)

    @Query("SELECT * FROM favorites")
    fun getAllFavourites(): LiveData<List<FavouriteItem>>
}
