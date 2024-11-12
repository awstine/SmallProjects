
package com.example.loginscreen.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginscreen.R

@Suppress("PreviewAnnotationInFunctionWithParameters")
@Composable
//@Preview
fun WelcomeScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.img_1),
            contentDescription = "Background image",
        contentScale = ContentScale.Crop
            )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //Log in button
                Button(
                    modifier = Modifier
                        .width(400.dp)
                        .height(120.dp) // Make the button taller
                        .padding(bottom = 50.dp),
                    elevation = ButtonDefaults.buttonElevation(6.dp),
                    onClick = { navController.navigate(Screens.CreateAccountScreen.route) },
                    shape = RoundedCornerShape(50.dp), // Curved ends
                    colors = ButtonDefaults.buttonColors(Color(0xFFFFC1CB)) // Pink background
                ) {
                    Text(
                        text = "Create Account",
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        color = Color.White, // White text
                        modifier = Modifier.padding(horizontal = 16.dp) // Add some padding for better text spacing
                    )
                }


                val annotatedString = buildAnnotatedString {
                    withStyle(style = ParagraphStyle()) {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append("Don't have an account? ")
                        }

                        pushStringAnnotation(tag = "SignUp", annotation = "signUp")
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("SignUp")
                        }
                        pop()
                    }
                }
                ClickableText(
                    modifier = Modifier
                        .padding(bottom = 200.dp),
                    text = annotatedString,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(
                            tag = "SignUp",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {
                            navController.navigate(Screens.SignUpScreen.route)
                        }
                    }
                )
            }
        }
    }
}