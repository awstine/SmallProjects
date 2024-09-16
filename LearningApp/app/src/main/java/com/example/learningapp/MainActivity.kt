package com.example.learningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningapp.data.today
import com.example.learningapp.data.todayList
import com.example.learningapp.ui.theme.LearningAppTheme
import com.example.learningapp.viewModel.LearningViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.fillMaxSize()
                ) {
                LearnUI()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LearnUI(viewModel: LearningViewModel = viewModel()) {
     val todayButtonColor by viewModel.todayButton
    val lerningButtonColor by viewModel.learningButton

    LearningAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { },
                    navigationIcon = {
                        Box(modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape)) {
                        Image(
                            painter = painterResource(R.drawable.img),
                            contentDescription =null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(50.dp),
                            )
                      }},
                    actions = {
                        Box(
                            modifier = Modifier
                                //  .padding(8.dp)
                                .size(55.dp)
                                .background(
                                    color = Color.LightGray,
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }

                        Spacer(modifier = Modifier.padding(7.dp))

                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(55.dp)
                                .background(
                                    Color.LightGray,
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                        }
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(15.dp)
            ) {
                Text(
                    text = "Hello",
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    style = typography.titleLarge,

                )
                Text(
                    text = "Mark Dagascos",
                    fontStyle = FontStyle.Italic,
                    fontSize = 35.sp,
                    style = typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                  // horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            viewModel.onTodayButtonClick()
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .weight(0.7f)
                            .height(80.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = todayButtonColor
                        )
                            //.size(150.dp, 80.dp)
                    ) {
                        Text(
                            text = "Today",
                            style = typography.bodyMedium.copy(Color.White),
                            fontSize = 20.sp,
                         )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            viewModel.onLearningButtonClick()
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .weight(1f)
                            .height(80.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = lerningButtonColor
                        )

                    ) {
                        Text(
                            text = "Learning Path",
                            fontSize = 20.sp 
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    items(todayList) { todayItem ->
                        todayCardContent(todayItem)
                    }
                }
            }
        }
    }
}

    @Suppress("PreviewAnnotationInFunctionWithParameters")
    @Composable
//@Preview(showBackground = true)
    fun todayCardContent(todayItem: today) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(8.dp)

        ) {
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            ) {
                //Background image
                Image(
                    painter = painterResource(todayItem.img),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //Bookmark Image
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(5.dp)
                                .background(Color.LightGray, CircleShape)
                        ) {
                            Image(
                                painter = painterResource(todayItem.bookmarkImage),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(8.dp)
                                    .align(Alignment.Center)
                            )
                        }

                    }
                    Text(
                        text = todayItem.title,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp),
                        style = typography.titleLarge
                    )
                    Text(
                        text = todayItem.description,
                        fontSize = 15.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(15.dp),
                        style = typography.bodyMedium
                    )
                    Text(
                        text = todayItem.name,
                        fontSize = 15.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp),
                    )
                }
                Button(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )

                ) {
                    Text(
                        modifier = Modifier
                            .padding(12.dp),
                        text = todayItem.lesson,
                        fontSize = 15.sp,
                        style = typography.labelMedium.copy(Color.White),
                    )
                }
            }
        }
    }
}
