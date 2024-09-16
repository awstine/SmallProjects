package com.example.loginscreen.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginscreen.R

@Composable
fun CreateAccountScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create an Account",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
        )

       SignUpBox(
           onClick = { /*TODO*/ },
           image = R.drawable.facebook,
           text = "Sign Up with Facebook",
           contentDescription = "Facebook",
           modifier = Modifier
       )

            SignUpBox(
                onClick = { /*TODO*/ },
                image = R.drawable.search,
                text = "Sign Up with Google",
                contentDescription = "Google",
                modifier = Modifier
            )

            SignUpBox(
                onClick = { /*TODO*/ },
                image = R.drawable.apple,
                text = "Sign Up with Apple",
                contentDescription = "Apple",
                modifier = Modifier
            )

            SignUpBox(
                onClick = { /*TODO*/ },
                image = R.drawable.email,
                text = "Sign Up with Email",
                contentDescription = "Email",
                modifier = Modifier
            )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding( 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
            )

            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "or",
                fontSize = 20.sp
            )

            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
            )
        }

        Button(
            modifier = Modifier
                .width(400.dp)
                .padding(bottom = 50.dp),
            onClick = { },
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Sign Up",
                fontSize = 30.sp,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        }

            Text(
                text = "Already have an account?",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 30.dp)
            )

            Text(
                text = "Log In",
                fontSize = 16.sp,
                color = Color.Blue,
                modifier = Modifier.padding(top = 10.dp).clickable {
                    navController.navigate(Screens.LogInScreen.route)
                }
            )
    }
}

@Composable
fun SignUpBox(
    onClick:()->Unit,
    image:Int,
    text: String,
    contentDescription: String,
    modifier: Modifier,
    imageSize: Int = 50,
    padding: Int = 15
){
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.dp),
        onClick = onClick,
     //   shape = MaterialTheme.shapes.medium
    ) {
        Image(painter = painterResource(id = image ),
            contentDescription = contentDescription,
            modifier = Modifier
                .size(imageSize.dp)
                .padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.headlineMedium
        )

    }
}