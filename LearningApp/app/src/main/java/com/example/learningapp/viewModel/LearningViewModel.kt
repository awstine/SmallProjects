package com.example.learningapp.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class LearningViewModel: ViewModel(){
    private val _todayButton = mutableStateOf(Color.Black)
    val todayButton: State<Color> = _todayButton

    private val _learningButton = mutableStateOf(Color.Black)
    val learningButton: State<Color> = _learningButton

    fun onTodayButtonClick(){
        _todayButton.value = if (_todayButton.value == Color.Black) {
            Color.LightGray
        } else Color.Black
    }

    fun onLearningButtonClick(){
        _learningButton.value = if (_learningButton.value == Color.Black){
            Color.LightGray
        }else Color.Black
    }
}