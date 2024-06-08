package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Composables.FavouriteScreen
import com.example.myapplication.Composables.GetStartedScreen
import com.example.myapplication.Composables.HomeScreen
import com.example.myapplication.Composables.LogInScreen
import com.example.myapplication.Composables.SignInScreen
import com.example.myapplication.data.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.GetStartedScreen.route,
                ) {
                    composable(route = Screen.GetStartedScreen.route) {
                        GetStartedScreen(navController)
                    }
                    composable(route = Screen.LogInScreen.route) {
                        LogInScreen(navController)
                    }
                    composable(route = Screen.HomeScreen.route) {
                        HomeScreen(navController)
                    }
                    composable(route = Screen.SignInScreen.route) {
                        SignInScreen(navController)
                    }
                    composable(route = Screen.FavouriteScreen.route) {
                        FavouriteScreen(navController = navController)
                    }
                }
            }
        }
    }
}
