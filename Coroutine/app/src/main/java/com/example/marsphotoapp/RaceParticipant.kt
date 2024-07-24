package com.example.marsphotoapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

class RaceParticipant(
    val maxProgress: Int = 100,
    private val progressIncrement: Int = 1,
    private val initialProgress: Int = 0,


){
    var currentProgress by mutableStateOf(initialProgress)
        private set
    suspend fun run(){
        while (currentProgress < maxProgress){
            delay(progressDelayMillis)
            currentProgress += progressIncrement
        }
    }
}