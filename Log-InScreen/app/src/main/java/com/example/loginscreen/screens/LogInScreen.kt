package com.example.loginscreen.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginscreen.R

@Composable
fun LogInScreen(navController: NavController){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Back",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
        )


        TextField(
            text = email,
            onValueChange = {email = it},
            placeholder = "Email",
            isPasswordField = false,
            modifier = Modifier

        )

        TextField(
            text = password,
            onValueChange = {password},
            placeholder = "Password",
            isPasswordField = true,
            modifier = Modifier
        )

        Button(
            modifier = Modifier
                .width(400.dp)
                .padding(bottom = 50.dp),
            onClick = { navController.navigate(Screens.HomeScreen.route) },
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Log In",
                fontSize = 30.sp,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        }

        //OR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier
                .weight(1f,)
                .height(1.dp))
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "or",
                fontSize = 20.sp
            )
            HorizontalDivider(modifier = Modifier
                .weight(1f,)
                .height(1.dp))
        }

        Row (
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ){
            LoginIcons(
                image = R.drawable.facebook,
                contentDescription = "Facebook"
            )
            LoginIcons(
                image = R.drawable.search,
                contentDescription = "Google"
            )
            LoginIcons(
                image = R.drawable.apple,
                contentDescription = "Apple"
            )
        }

        val annotatedString = buildAnnotatedString {
            append("Don't have an account? ")

            pushStringAnnotation(tag = "SignUp", annotation = "signUp")
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("SignUp")
            }
            pop()
        }
        ClickableText(
            modifier = Modifier
                .padding(top = 70.dp),
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(
                    tag = "SignUp",
                    start = offset,
                    end = offset
                )
                    .firstOrNull()?.let {
                        //navcontroller.navigate("SignUp")
                    }
            }
        )
    }
}

@Composable
fun LoginIcons(
    image: Int,
    boaderColor: Color = Color.LightGray,
    boxsize: Int = 75,
    imageSize: Int = 40,
    contentDescription: String
){
    Box (
        modifier= Modifier
            .size(boxsize.dp)
            .border(
                BorderStroke(2.dp, boaderColor),
                shape = MaterialTheme.shapes.small
            ),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier
                .size(imageSize.dp),
            painter = painterResource(id = image),
            contentDescription = contentDescription
        )
    }
}


@Composable
fun TextField(
    text: String,
    modifier: Modifier,
    placeholder: String,
    onValueChange: (String)->Unit,
    isPasswordField:Boolean = false
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