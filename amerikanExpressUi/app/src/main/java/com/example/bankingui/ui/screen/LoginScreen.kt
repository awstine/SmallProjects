package com.example.bankingui.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bankingui.R

@Composable
@Preview
fun LoginScreen() {
    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
    ){
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
        )
        Spacer(Modifier.height(4.dp))

        Text(
            "Good Afternoon!"
        )

    }
}