package com.example.loginscreen.Auth

data class SignInState (
    val isSignedInSuccessful: Boolean = false,
    val signInError: String? = null
)