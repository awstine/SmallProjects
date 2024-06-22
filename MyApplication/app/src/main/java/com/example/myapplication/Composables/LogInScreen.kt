@file:Suppress("ktlint:standard:package-name")

package com.example.myapplication.Composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun LogInScreen(
    navController: NavController,
    auth: FirebaseAuth,
    onSignedIn: (FirebaseUser?) -> Unit,
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var isLoggedIn by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isButtonEnabled = firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()

    // Error message
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier =
            Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = if (isLoggedIn) "Sign In" else "Create Account",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onTertiary,
            fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
        )
        if (!isLoggedIn) {
            InputField(
                value = firstName,
                onValueChange = { firstName = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                    )
                },
                label = "First Name",
                modifier = Modifier.padding(8.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            InputField(
                value = lastName,
                onValueChange = { lastName = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                    )
                },
                label = "Last Name",
                modifier = Modifier.padding(8.dp),
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        InputField(
            value = email,
            onValueChange = { email = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                )
            },
            label = "Email",
            modifier = Modifier.padding(8.dp),
            keyboardOptions =
                KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                ),
        )
        if (!email.contains("@") && email.isNotEmpty()) {
            Row {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                )
                Text(
                    text = "Invalid email address",
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            value = password,
            onValueChange = { password = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                )
            },
            keyboardOptions =
                KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                ),
            label = "Password",
            modifier = Modifier.padding(8.dp),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible },
                ) {
                    val icon =
                        if (isPasswordVisible) {
                            Icons.Default.CheckCircle
                        } else {
                            Icons.Default.Close
                        }
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                }
            },
        )

        Spacer(modifier = Modifier.height(8.dp))
        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            enabled = isButtonEnabled,
            onClick = {
                isLoading = true
                if (isLoggedIn) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            isLoading = false
                            if (task.isSuccessful) {
                                onSignedIn(auth.currentUser)
                            } else {
                                errorMessage = task.exception?.message
                            }
                        }
                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            isLoading = false
                            if (task.isSuccessful) {
                                onSignedIn(auth.currentUser)
                            } else {
                                errorMessage = task.exception?.message
                            }
                        }
                }
            },
            modifier =
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onSurface)
            } else {
                Text(
                    text = if (isLoggedIn) "Login" else "Create Account",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text =
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append(
                            if (isLoggedIn) "Don't have an account? Sign Up" else "Already have an account? Login",
                        )
                    }
                },
            modifier =
                Modifier.clickable {
                    errorMessage = null
                    isLoggedIn = !isLoggedIn
                },
        )
    }
}

private fun onLogInError(errorMessage: String) {
    println("Sign in Error: $errorMessage")
}

@Suppress("klint:standard:function-naming", "ktlint:standard:function-naming")
@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = leadingIcon,
        modifier =
            Modifier
                .padding(5.dp)
                .fillMaxWidth(),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
    )
}
//
//
