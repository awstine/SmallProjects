package com.example.bankingui.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        //verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
    ) {
        Spacer(Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .height(100.dp)
        )


        Text("Good Afternoon!")

        // User ID
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("User Id")
                OutlinedTextField(
                    value = userId,
                    onValueChange = { userId = it },
                    placeholder = { Text("Enter your User ID") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Password
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Password")
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Enter your password") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icons.Default.Lock
                    }
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp,)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.shield),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 7.dp)
                        .size(25.dp)
                )
                Text("Security Tips")
            }
            Text("New User?")

        }

        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 6.dp,)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Forgot User ID",
                    color = Color.Blue
                )
            }
            Text("Forgot Password?")

        }

        //Login button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                "Login",
                modifier = Modifier
                    .padding(8.dp)
            )
        }

        //Fingerprint
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.fingerprint),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 7.dp)
                    .size(22.dp)
            )
            Text("Use Fingerprint")
        }

        Spacer(Modifier.height(170.dp))
        Row {
            HorizontalDivider(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                "Change Language",
            )
        }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("English")
            }
            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(1f),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Spanish")
            }
        }

        Row {
            HorizontalDivider(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Apply now
            Box(
                modifier = Modifier
                    .size(30.dp) // Adjust size as needed
                    .background(Color.LightGray, CircleShape) // Circular background
                    .padding(8.dp) // Padding inside the circle
            ) {
                Image(
                    painter = painterResource(R.drawable.registration),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text("Apply now", modifier = Modifier.padding(start = 8.dp))

            // Amex offers
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.LightGray, CircleShape)
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.offer),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text("Amex offers", modifier = Modifier.padding(start = 8.dp))

            // Resume application
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.LightGray, CircleShape)
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.resume),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text("Resume application", modifier = Modifier.padding(start = 8.dp))
        }
    }
}
