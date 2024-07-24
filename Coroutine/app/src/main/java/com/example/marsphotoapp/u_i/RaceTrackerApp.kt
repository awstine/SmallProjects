package com.example.marsphotoapp.u_i

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun RaceTrackerApp(){
    var raceInProgress by remember { mutableStateOf(false)}

    LaunchedEffect(playerOne, playerTwo) {
        coroutineScope {
            launch {
                playerOne.run()
            }
            launch {
                playerTwo.run()
            }
        }
        raceInProgress = false
    }
    RaceTrackerScreen()
}