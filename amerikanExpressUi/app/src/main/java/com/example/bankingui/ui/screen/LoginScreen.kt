package com.example.bankingui.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bankingui.R

@Composable
//@Preview
fun LoginScreen() {
    var userId by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
    ){
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .height(100.dp)
        )
        Spacer(Modifier.height(4.dp))

        Text(
            "Good Afternoon!"
        )

        //User id
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column (
                modifier = Modifier
            ){
                Text("User Id")

                OutlinedTextField(
                    value = userId,
                    onValueChange = { userId = it },
                    label = { Text("Enter yor User id") }
                )
            }
        }

        //Password
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column (
                modifier = Modifier
            ){
                Text("Password")

                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it },
                    label = { Text("Enter your password") },
//                    visualTransformation =
//                        if (passwordVisible)
//                            VisualTransformation.None
//                        else
//                            PasswordVisualTransformation(),
                    trailingIcon = {
                        Icons.Default.Lock
//                        val image =
//                            if (passwordVisible)
//                                Icons.Default.Visibility
//                            else
//                                Icons.Default.VisibilityOff
                                            }
                )
            }
        }
    }
}
