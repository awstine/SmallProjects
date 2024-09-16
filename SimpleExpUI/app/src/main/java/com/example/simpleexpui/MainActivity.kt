@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.simpleexpui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpleexpui.data.activities
import com.example.simpleexpui.data.experiences
import com.example.simpleexpui.data.places
import com.example.simpleexpui.ui.theme.SimpleExpUITheme
import androidx.compose.ui.graphics.vector.ImageVector

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleExpUITheme {
                MainScreen()
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview(showBackground = true)
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(35.dp),
                            painter = painterResource(id = R.drawable.be),
                            contentDescription = "Belair"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(
                onHomeClick = { /*TODO*/ },
                onSearchClick = { /*TODO*/ },
                onFavoritesClick = { /*TODO*/ }
            )
        }
    ){paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            // val isSelected remember { mutableStateOf(0) }

            Text(
                text = "Find Your Experience",
                fontSize = 40.sp,
                modifier = Modifier.padding(9.dp),
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 28.sp,
                letterSpacing = 0.15.sp,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily


            )
            LazyRow (
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(10.dp)
            ){
                items(experiences){ experience ->
                    //CARDS

                    var isSelected by remember { mutableStateOf(false) }

                    Surface (
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable { isSelected = !isSelected }, // Toggle isSelected on click
                        color = if (isSelected) Color.Blue else Color.Transparent,
                        shape = RoundedCornerShape(16.dp),
                        ){
                    Card(
                        modifier = Modifier
                            .padding(5.dp),
                    ){
                        Column (
                            modifier = Modifier
                                .padding(10.dp)
                        ){
                            Text(
                                text = experience.name,
                                fontSize = 15.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Black
                            )
                        }
                    }
                        }
                }
            }
            LazyRow(
                modifier = Modifier
                    .background(Color.Transparent)
            ) {
                items(places){ place ->
                    //SEction 2
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(width = 140.dp, height = 250.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                    ){
                        Column {
                            Image(
                                painter = painterResource(id = place.image),
                                contentDescription = place.name,
                                modifier = Modifier
                                    .size(170.dp)
                                    .padding(15.dp)
                            )
                            Text(
                                text = place.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 19.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Black
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                  //  .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "Location"
                                )
                                Text(text = place.address)
                            }
                        }
                    }
                }

            }
            //SECTION 3
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "What Do You like",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "View All",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(15.dp)
                )
            }
            LazyRow(
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(12.dp)
            ) {
                items(activities){ activity ->
                    Column(
                        modifier = Modifier
                            .padding(13.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = activity.image),
                            contentDescription = activity.name,
                            modifier = Modifier
                                .size(80.dp)
                                // .padding(20.dp)
                                .clip(CircleShape)
                        )
                        Row {
                            Text(
                                text = activity.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Black
                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically // Align content vertically
        ) {
            BottomBarItem(
                icon = Icons.Default.Home,
                label = "Home",
                onClick = onHomeClick
            )
            BottomBarItem(
                icon = Icons.Default.Search,
                label = "Search",
                onClick = onSearchClick
            )
            BottomBarItem(
                icon = Icons.Default.Favorite,
                label = "Favorites",
                onClick = onFavoritesClick
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp)) // Add space between icon and text
        Text(label)
    }
}