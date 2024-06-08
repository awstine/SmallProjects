@file:Suppress("ktlint:standard:package-name")

package com.example.myapplication.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.Screen

@Composable
@Suppress("ktlint:standard:function-naming")
fun GetStartedScreen(navController: NavController) {
    Surface {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Bottom, // Center the content vertically
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier =
                    Modifier
                        .fillMaxWidth(0.8f)
                        .padding(bottom = 140.dp),
                painter = painterResource(id = R.drawable.saloon),
                contentDescription = null,
            )
            Text(
                "Welcome to Our Saloon App",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp),
            )
            Button(
                onClick = {
                    navController.navigate(Screen.LogInScreen.route)
                },
                modifier =
                    Modifier
                        .fillMaxWidth(0.8f) // 80% of available width
                        .padding(vertical = 16.dp),
            ) {
                Text(text = "Get Started")
            }
        }
    }
}
