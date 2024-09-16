@file:Suppress("ktlint:standard:package-name")

package com.example.myapplication.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.Screen
import com.example.myapplication.ui.theme.AlegrayFamilyFontFamily
import com.example.myapplication.ui.theme.AlegraySonsFamily

@Composable
@Suppress("ktlint:standard:function-naming")
fun GetStartedScreen(navController: NavController) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.White),
    ) {
        Image(
            painter = painterResource(id = R.drawable.saloon_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Welcome to Our Saloon App",
                fontFamily = AlegrayFamilyFontFamily,
                fontSize = 45.sp,
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 120.dp),
            )
            ElevatedButton(
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                },
                modifier =
                    Modifier
                        .padding(bottom = 80.dp)
                        .fillMaxWidth(0.8f)
                        .height(60.dp),
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
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    fontFamily = AlegraySonsFamily
                )
            }
        }
    }
}

//    Surface(
//        color = MaterialTheme.colorScheme.tertiary,
//        contentColor = MaterialTheme.colorScheme.onBackground,
//    ) {
//        Box(
//            modifier = Modifier.fillMaxSize(),
//        ) {

//            Spacer(modifier = Modifier.height(16.dp))
//            Image(
//                modifier = Modifier,
//                painter = painterResource(id = R.drawable.saloon_background2),
//                contentDescription = null,
//                contentScale = ContentScale.FillBounds,
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            }
//        }
//    }
// }
