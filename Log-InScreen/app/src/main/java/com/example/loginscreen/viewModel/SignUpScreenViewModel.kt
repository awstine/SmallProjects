package com.example.loginscreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel(){

    private val _firstName = mutableStateOf("")
    val firstName: State<String> = _firstName

    private val _lastName = mutableStateOf("")
    val lastName: State<String> = _lastName

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password


    fun FirstNameChange(newFirstName: String){
        _firstName.value = newFirstName
    }

    fun LastNameChange(newLastName: String){
        _lastName.value = newLastName
    }

    fun EmailChange(newEmail: String){
        _email.value = newEmail
    }

    fun PasswordChange(newPassword: String){
        _password.value = newPassword
    }

    //placeholder for signIn
    fun signUp(onSignedUp: () -> Unit,onError: () -> Unit){
        if (
            _firstName.value.isNotEmpty() &&
            _lastName.value.isNotEmpty() &&
            _email.value.isNotEmpty() &&
            _email.value.contains("@") &&
            _password.value.isNotEmpty()
        ){
            onSignedUp()
        }else{
            onError()
        }
    }

}