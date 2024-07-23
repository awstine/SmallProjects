package com.example.myapplication.data2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteItem::class], version = 1)
abstract class SaloonDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao

    companion object {
        @Suppress("ktlint:standard:property-naming")
        @Volatile
        private var INSTANCE: SaloonDatabase? = null

        fun getDatabase(context: Context): SaloonDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            SaloonDatabase::class.java,
                            "saloon_database",
                        ).build()
                    INSTANCE = instance
                    instance
                }
        }
    }
}
