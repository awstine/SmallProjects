package com.example.loginscreen.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SignUpScreen(
    navController: NavController
){

}

@Composable
fun TextField(
    text: String,
    placeholder: String,
    modifier: Modifier,
    onValueChange: (String)->Unit,
    isPasswordField: Boolean = false
){
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder)},
        modifier = Modifier
            .fillMaxWidth()
            //.width(150.dp)
            .padding(30.dp)
    )
}