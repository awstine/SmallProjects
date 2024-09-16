package com.example.loginscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginscreen.Auth.SignInViewModel
import com.example.loginscreen.screens.CreateAccountScreen
import com.example.loginscreen.screens.HomeScreen
import com.example.loginscreen.screens.LogInScreen
import com.example.loginscreen.screens.WelcomeScreen
import com.example.loginscreen.screens.Screens
import com.example.loginscreen.ui.theme.LogInScreenTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogInScreenTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screens.WelcomeScreen.route
                ) {
                    composable(route = Screens.WelcomeScreen.route) {
                        WelcomeScreen(navController = navController)
                    }
                    composable(route = Screens.LogInScreen.route) {
                        LogInScreen(navController = navController)
                    }
                    composable(route = Screens.CreateAccountScreen.route) {
                        CreateAccountScreen(navController = navController)
                    }
                    composable(route = Screens.HomeScreen.route) {
                        HomeScreen(navController = navController)
                    }
                }
            }
        }
    }
}