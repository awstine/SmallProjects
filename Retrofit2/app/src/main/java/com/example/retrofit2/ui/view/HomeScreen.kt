package com.example.retrofit2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.retrofit2.R
import com.example.retrofit2.model.Digimon

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(digimon: Digimon) {
    Card(
        modifier = Modifier
            .padding(8.dp,4.dp)
            .fillMaxWidth()
            .height(110.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Surface {
            Row (
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ){
                Image(
                    painter = rememberImagePainter(data = digimon.imageUrl,
                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.nodata)
                            transformations(CircleCropTransformation())
                        }),
                    contentDescription = "Digimon Image",
                )

                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = digimon.name,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                           // .background(Color.LightGray)
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = digimon.level,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                           // .background(Color.LightGray)
                            .padding(4.dp),
                        fontSize = 22.sp
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun View1() {
    val digi = Digimon("Agumon", " ", "Rookie")
    HomeScreen(digimon = digi)
}