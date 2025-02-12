package com.example.valentainapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = LocalConfiguration.current.screenHeightDp.dp/3)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White
                            ),
                            startY = -1f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

                Column(
                    modifier = Modifier
                        .padding(top = 76.dp, start = 35.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Happy",
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 46.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Valentine's Day",
                        style = MaterialTheme.typography.headlineLarge,
                        fontSize = 46.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


            // Image in the center
            Row (
                modifier = Modifier
                    .padding(top = 170.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.glass),
                        contentDescription = "Heart",
                        modifier = Modifier
                            .size(290.dp)
                    )
                }
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

        Row{
          HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
          )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Romantic Sticker Pack",
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

        RomanticGrid(romanticList = romanticList)

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
            )
        }
    }
}

@Composable
fun RomanticGrid(
    romanticList: List<Romantic>
) {
    val cardColors = listOf(
        Color(0xFFFFCCCB),
        Color(0xFFFFB6C1),
        Color(0xFFFF69B4)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(romanticList){romantic ->
            val randomColor = cardColors.random()
            RomanticCards(romantic = romantic,cardColor = randomColor)
        }

    }
}

@Composable
fun RomanticCards(
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
}
