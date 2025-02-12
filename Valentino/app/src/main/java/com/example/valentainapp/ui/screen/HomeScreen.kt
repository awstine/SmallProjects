package com.example.valentainapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.valentainapp.R


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier

            .fillMaxSize(),
     //   verticalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(top = 76.dp, start = 35.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Happy",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 46.sp,
                fontWeight = FontWeight.Thin
            )
            Text(
                text = "Valentine's Day",
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 46.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Image in the center
        Row {
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.glass),
                    contentDescription = "Heart",
                    modifier = Modifier
                        .size(200.dp)
                )
            }
        }

        // Trending Stickers and See All button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Trending Stickers",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 30.sp,
                )
                TextButton(
                    onClick = { /*TODO*/ },
                ) {
                    Text(
                        text = "See All",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

        TrendingStickers(trendingList = trendingList)

    }
}

@Composable
fun TrendingStickers(
    trendingList: List<Trending>
   ) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(trendingList.size) { index ->
            Image(
                painter = painterResource(id = trendingList[index].image),
                contentDescription = "Trending Sticker",
                modifier = Modifier
                    .size(180.dp)
                    .padding(8.dp)
            )
        }
    }
}