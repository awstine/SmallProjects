package com.example.retrofit2.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit2.model.Digimon
import com.example.retrofit2.network.ApiService
import kotlinx.coroutines.launch

class DigimonViewModel: ViewModel() {
    var digimonListResponce: List<Digimon> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getDigimonList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()

            try {
                val digimonList = apiService.getDigimons()
                digimonListResponce = digimonList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}