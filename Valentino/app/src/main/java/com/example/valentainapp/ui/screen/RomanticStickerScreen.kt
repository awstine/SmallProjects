@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.valentainapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RomanticStickerScreen(
    romanticList: List<Romantic>,
    navController: NavController
) {

    val cardColors = listOf(
        Color(0xFFFFCCCB),
        Color(0xFFFFB6C1),
        Color(0xFFFF69B4)
    )
    Scaffold (
        modifier = Modifier
            .background(Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Romantic Sticker Pack",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )

                    }
                },
            )
        }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
                .background(Color.White)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(romanticList) { romantic ->
                    val randomColor = cardColors.random()
                    RomanticCard(romantic = romantic, cardColor = randomColor)
                }
            }

        }
    }
}


@Composable
fun RomanticCard(
    romantic: Romantic,
    cardColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = romantic.image),
                contentDescription = "Romantic Sticker",
                modifier = Modifier
                    .size(190.dp)
            )

            Text(
                text = romantic.title,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = romantic.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

        }
    }

//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .aspectRatio(1f),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(8.dp)
//        ) {
//            Image(
//                painter = painterResource(romantic.image),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(80.dp)
//            )
//            Text(
//                text = romantic.title,
//                style = MaterialTheme.typography.headlineMedium
//            )
//            Text(
//                text = romantic.subtitle,
//                style = MaterialTheme.typography.headlineMedium
//            )
//
//        }
//
//    }
}














