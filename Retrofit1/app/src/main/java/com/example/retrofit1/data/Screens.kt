package com.example.retrofit1.data

sealed class Screens(val route: String){
    object HomeScreen: Screens("homeScreen")
    object DetailScreen: Screens("detailScreen")
}