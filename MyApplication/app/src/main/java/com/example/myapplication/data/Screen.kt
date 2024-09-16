package com.example.myapplication.data

sealed class Screen(
    val route: String,
) {
    object GetStartedScreen : Screen("GetStarted")

    object LogInScreen : Screen("LogIn")

    object HomeScreen : Screen("Home")

    object SignInScreen : Screen("SignIn")

    object FavouriteScreen : Screen("Favourite")

    object StylistScreen : Screen("Stylist")
}
