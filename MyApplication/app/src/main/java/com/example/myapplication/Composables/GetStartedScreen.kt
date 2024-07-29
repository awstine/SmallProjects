@file:Suppress("ktlint:standard:package-name")

package com.example.myapplication.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.Screen

@Composable
@Suppress("ktlint:standard:function-naming")
fun GetStartedScreen(navController: NavController) {
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    // .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
            verticalArrangement = Arrangement.Center, // Center the content vertically
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Welcome to Our Saloon App",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center, // Center align text
                modifier = Modifier.padding(16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                modifier =
                    Modifier
                        .fillMaxWidth(0.8f)
                        .padding(bottom = 32.dp),
                // Adjusted padding for a better layout
                painter = painterResource(id = R.drawable.saloon),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedButton(
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                },
                modifier =
                    Modifier
                        .fillMaxWidth(0.8f) // 80% of available width
                        .padding(vertical = 16.dp)
                        .size(60.dp),
                elevation =
                    ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 8.dp,
                    ),
                shape = RoundedCornerShape(30.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
            ) {
                Text(
                    text = "Get Started",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center, // Center align text
                )
            }
        }
    }
}
