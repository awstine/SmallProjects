package com.example.datapersistence.repository

import com.example.datapersistence.data.Item
import com.example.datapersistence.data.ItemDao
import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao): ItemsRepository {
    override fun getAllItemsStream(): Flow<List<Item>> {
        TODO("Not yet implemented")
    }

    override fun getItemStream(id: Int): Flow<Item?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertItem(item: Item) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(item: Item) {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(item: Item) {
        TODO("Not yet implemented")
    }
}