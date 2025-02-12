package com.example.valentainapp.ui.screen

sealed class Screen(val route: String){
    object HomeScreen: Screen("home")
    object RomanticStickerScreen: Screen("romanticSticker")
}