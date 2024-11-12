package com.example.retrofit1.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit1.data.api.model.Character
import com.example.retrofit1.data.repository.CharactersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val charactersRepo: CharactersRepo,
    @ApplicationContext context: Context
): ViewModel() {

    //We create a MutableStateFlow to store the list of characters
    private val _state = MutableStateFlow(emptyList<Character>())
    val state: StateFlow<List<Character>>
        get() = _state

    init {
        viewModelScope.launch {
            val characters = charactersRepo.getCharacters()
            _state.value = characters
        }
    }

    private val networkObserver = NetworkConnectivityObserver(context)

    val isConnected: StateFlow<Boolean> = networkObserver.isConnected
        .stateIn(viewModelScope, SharingStarted.Lazily, false)
}