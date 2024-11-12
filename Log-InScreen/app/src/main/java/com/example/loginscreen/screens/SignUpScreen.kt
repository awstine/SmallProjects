@file:Suppress("UNUSED_EXPRESSION")

package com.example.loginscreen.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.isPopupLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.loginscreen.viewModel.SignUpViewModel
import kotlin.math.sign


@Composable
fun SignUpScreen(
    navController: NavController,
    signUpViewModel: SignUpViewModel = viewModel()
){

  //  var isLoading by remember { mutableStateOf(false) }
  //  var isSignedIn by remember { mutableStateOf(true) } //check sign in status
  //  var isPasswordVisible by remember { mutableStateOf(false) } //not showing
    var errorMessage by remember { mutableStateOf("") }

   // if (!isSignedIn) {
        //sign in
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "Create an Account",
                modifier = Modifier.padding(30.dp)
            )
            TextField(
                text = signUpViewModel.firstName.value,
                onValueChange = signUpViewModel::FirstNameChange,
                placeholder = "First Name",
                modifier = Modifier,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
            TextField(
                text = signUpViewModel.lastName.value,
                placeholder = "Last Name",
                modifier = Modifier,
                onValueChange =  signUpViewModel::LastNameChange,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )

            TextField(
                text = signUpViewModel.email.value,
                placeholder = "Email",
                modifier = Modifier,
                onValueChange = signUpViewModel::EmailChange,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                )
            )
            TextField(
                text = signUpViewModel.password.value,
                placeholder = "Password",
                modifier = Modifier,
                onValueChange =  signUpViewModel::PasswordChange,
                isPasswordField = VisualTransformation.None,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
//                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
//                        Icon(
//                            imageVector = if (isPasswordVisible) Icons.Default.Lock else Icons.Filled.Person,
//                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
//                        )
//                    }
                }
            )
            //error message
//            if (errorMessage != null) {
//                Text(
//                    text = errorMessage!!,
//                    modifier = Modifier.padding(30.dp),
//                    color = Color.Red
//                )
//            }else { }

            if (errorMessage.isNotEmpty()){
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = errorMessage,
                    color = Color.Red
                )
            }

            Button(
                modifier = Modifier
                    .width(400.dp)
                    .padding(bottom = 50.dp),
                onClick = {
                    signUpViewModel.signUp(
                        onSignedUp = {
                            navController.navigate(Screens.CreateAccountScreen.route)
                        },
                        onError = {
                            errorMessage = "Invalid Credentials "
                        }
                    )
                },
//                    if (isSignedIn) {
//                        signIn(
//                            auth = auth,
//                            email = email,
//                            password = password,
//                            onSignedIn = { signedInUser ->
//                                onSignedIn(signedInUser)
//                            },
//                            onSignInError = { error ->
//                                errorMessage = error
//                            }
//                        )
//                    } else {
//                        signUp(
//                            auth = auth,
//                            email = email,
//                            password = password,
//                            firstName = firstName,
//                            lastName = lastName,
//                            onSignedIn = { signedInUser ->
//                                onSignedIn(signedInUser)
//                            },
//                            onSignUpError = { error ->
//                                errorMessage = error
//                            }
//                        )
//                    }
//                }
        ){
            Text(
                text = "Sign Up",
                fontSize = 30.sp,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        }


            val annotatedString = buildAnnotatedString {
                append("Already have an account? ")

                pushStringAnnotation(tag = "Log In", annotation = "Log In")
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Log In")
                }
                pop()
            }
            ClickableText(
                modifier = Modifier
                    .padding(bottom = 200.dp),
                text = annotatedString,
                onClick = { offset ->
                    annotatedString.getStringAnnotations(
                        tag = "Log In",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        navController.navigate(Screens.LogInScreen.route)
                    }
                }
            )

        }

}

//private fun signUp(
//    auth: FirebaseAuth,
//    email: String,
//    password: String,
//    firstName: String,
//    lastName: String,
//    onSignedIn: (user: FirebaseUser) -> Unit,
//    onSignUpError: (user: FirebaseUser) -> Unit
//){
//    auth.createUserWithEmailAndPassword(email,password)
//        .addOnCompleteListener{task->
//            if (task.isSuccessful){
//                val user = auth.currentUser
//                //create user profile
//                val userProfile = hashSetOf(
//                    "firstName" to firstName,
//                    "lastName" to lastName,
//                    "email" to email
//                )
//
//                val firestore = FirebaseFirestore.getInstance()
//                firestore.collection("users")
//                    .document(user!!.uid)
//                    .set(userProfile)
//                    .addOnSuccessListener {
//                        onSignedIn(user)
//                    }
//                    .addOnFailureListener {
//                      //  onSignUpError(it.localizedMessage?: "FireStore error")
//                        //exception for failure
//                    }
//            }else{
//                //handle sign in failure
//               // onSignUpError(task.exception?.localizedMessage?: "Sign up failed")
//            }
//        }
//}

//Handle sign in errors
private fun onSignInError(errorMessage: String){
    println("Sign in Error: $errorMessage")
}



@Composable
fun TextField(
    text: String,
    placeholder: String,
    modifier: Modifier,
    onValueChange: (String)->Unit,
    isPasswordField: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions,
    trailingIcon: @Composable (() -> Unit)? = null
){
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder)},
        modifier = Modifier
            .fillMaxWidth()
            //.width(150.dp)
            .padding(30.dp)
    )
}