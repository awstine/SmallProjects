package com.example.noteapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.Note
import com.example.noteapp.data.NoteDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val  dao: NoteDao
):ViewModel(){

    //Notes to be displayed by the date they were added
    private val isSortedByDateAdded = MutableStateFlow(true)

    //Check the sorting
    @OptIn(ExperimentalCoroutinesApi::class)
    private var notes =
        isSortedByDateAdded.flatMapLatest { sort ->
            if (sort){
                dao.getNotesOrderedByDateAdded()
            }else{
                dao.getNotesOrderedByTitle()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

        val _state = MutableStateFlow(NoteState())
        val state =
            combine(_state, isSortedByDateAdded, notes){state, isSortedByDateAdded, notes ->
                state.copy(
                    notes = notes
                )
            }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),NoteState())


    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.DeleteNote ->{
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }
            is NotesEvent.SaveNote -> {
                val note = Note(
                        title = state.value.title.value,
                        description = state.value.description.value,
                        dateAdded = System.currentTimeMillis()
                    )
                viewModelScope.launch {
                    dao.upsertNote(note)
                }
                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        description = mutableStateOf("")
                    )
                }
            }
            NotesEvent.SortNotes -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }
        }
    }
}