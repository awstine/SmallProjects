package com.example.statemanagement

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview


@SuppressLint("UnrememberedMutableState", "AutoboxingStateCreation")
@Composable
@Preview
fun WaterCounter(){
    //Remember only helps to remain the state but remember saveable
    // checks on its  configuration too
    var count by rememberSaveable{mutableStateOf(0)}
    Column (){
        if(count>0){
            Text(
                "You have clicked ${count} times"
            )
        }
        Button(
            onClick = { count++ },
            enabled = count < 10
        ) {
            Text(text = "Add One")
        }
    }
}