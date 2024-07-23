package com.example.datapersistence.data

import android.content.Context
import com.example.datapersistence.repository.ItemsRepository
import com.example.datapersistence.repository.OfflineItemsRepository




interface AppContainer {
    val itemsRepository: ItemsRepository
}


class AppDataContainer(private val context: Context): AppContainer{
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(ItemDatabase.getDatabase(context).itemDao())
    }
}