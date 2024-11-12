package com.example.loginscreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LogInViewModel(): ViewModel(){

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    fun onEmailChange(newMail: String){
        _email.value = newMail
    }

    fun onPasswordChange(newPassword: String){
        _password.value = newPassword
    }

    //placeHolder for sign in logic
    fun signIn(onSignedIn: () -> Unit,onError: () -> Unit){
        if (_email.value.isNotEmpty()
            && _password.value.isNotEmpty()
            && _email.value.contains("@")
            ){
            onSignedIn()
        }else {
            onError()
        }
    }

}