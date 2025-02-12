package com.example.valentainapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valentainapp.ui.screen.HomeScreen
import com.example.valentainapp.ui.screen.Romantic
import com.example.valentainapp.ui.screen.RomanticStickerScreen
import com.example.valentainapp.ui.screen.Screen
import com.example.valentainapp.ui.screen.romanticList
import com.example.valentainapp.ui.theme.ValentainAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ValentainAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.HomeScreen.route
                ) {
                composable(Screen.HomeScreen.route){
                    HomeScreen(navController = navController)
                }
                 composable(Screen.RomanticStickerScreen.route){
                     RomanticStickerScreen(romanticList = romanticList)
                 }
                }
            }
        }
    }
}