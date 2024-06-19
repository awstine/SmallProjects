package com.example.datapersistence.repository

import com.example.datapersistence.data.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    //Retrieve items from data source
    fun getAllItemsStream(): Flow<List<Item>>

    //Retrieve item from data source by id
    fun getItemStream(id: Int): Flow<Item?>

    //Insert item in data source
    suspend fun insertItem(item: Item)

    //Delete an item
    suspend fun deleteItem(item: Item)

    //Update item
    suspend fun updateItem(item: Item)
}