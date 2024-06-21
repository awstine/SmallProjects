@file:Suppress("ktlint:standard:package-name")

package com.example.myapplication.Composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.data.Screen
import com.google.firebase.auth.FirebaseAuth

@Suppress("ktlint:standard:function-naming")
@Composable
fun SignInScreen(
    navController: NavController,
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignedIn: (FirebaseAuth) -> Unit,
    onSignedInError: (String) -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isButtonEnabled = email.isNotEmpty() && password.isNotEmpty()

    Column(
        modifier =
            Modifier.padding(15.dp)
                .fillMaxWidth()
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Log In",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onPrimary,
            fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
        )
        Spacer(modifier = Modifier.height(6.dp))
        InputField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
        )
        Spacer(modifier = Modifier.height(6.dp))
        InputField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
        )
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = {
                navController.navigate(Screen.HomeScreen.route)
            },
            enabled = isButtonEnabled,
            modifier =
                Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Log In",
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
    )
}
