package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    //Upsert is able to insert and update
    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note ORDER By dateAdded")
    fun getNotesOrderedByDateAdded(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER By title ASC")
    fun getNotesOrderedByTitle(): Flow<List<Note>>
}