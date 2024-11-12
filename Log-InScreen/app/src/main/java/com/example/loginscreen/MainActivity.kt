package com.example.loginscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginscreen.screens.CreateAccountScreen
import com.example.loginscreen.screens.HomeScreen
import com.example.loginscreen.screens.LogInScreen
import com.example.loginscreen.screens.WelcomeScreen
import com.example.loginscreen.screens.Screens
import com.example.loginscreen.screens.SignUpScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screens.WelcomeScreen.route
                ) {
                    composable(route = Screens.WelcomeScreen.route){
                        WelcomeScreen(navController = navController)
                    }
                    composable(route = Screens.SignUpScreen.route) {
                        SignUpScreen(navController = navController)
                    }
                    composable(route = Screens.HomeScreen.route) {
                        HomeScreen(navController = navController)
                    }
                    composable(route = Screens.LogInScreen.route) {
                        LogInScreen(navController = navController)
                    }
                    composable(route = Screens.CreateAccountScreen.route) {
                        CreateAccountScreen(navController = navController)
                    }

                }
            }
        }
}