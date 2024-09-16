package com.example.loginscreen.screens

sealed class Screens (val route: String){
    object LogInScreen: Screens("logInScreen")
    object CreateAccountScreen: Screens("createAccountScreen")
    object WelcomeScreen: Screens("welcomeScreen")
    object HomeScreen: Screens("homeScreen")
    object SignUpScreen: Screens("signUpScreen")
}