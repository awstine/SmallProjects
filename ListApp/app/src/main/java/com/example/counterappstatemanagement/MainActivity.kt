package com.example.counterappstatemanagement

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counterappstatemanagement.ui.theme.CounterAppStateManagementTheme

@Suppress("UNUSED_EXPRESSION")
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterAppStateManagementTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Inbox") },
                            navigationIcon = {
                                IconButton(onClick = { /* Handle menu icon click */ }) {
                                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                                }
                            },
                            actions = {
                                IconButton(onClick = { /* Handle search icon click */ }) {
                                    Icon(Icons.Filled.Search, contentDescription = "Search")
                                }
                                IconButton(onClick = { /* Handle search icon click */ }) {
                                    Icon(Icons.Filled.MoreVert, contentDescription = "Search")
                                }
                            },
                        )
                    },
                    content = {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            items(characters) { People ->
                                Card(
                                    modifier =
                                        Modifier
                                            .padding(14.dp).fillMaxWidth(),
                                    // elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            painter = painterResource(id = People.image),
                                            contentDescription = null,
                                            modifier =
                                                Modifier
                                                    .padding(10.dp)
                                                    .size(50.dp)
                                                    .clip(CircleShape),
                                        )
                                        Column(modifier = Modifier.padding(start = 10.dp)) {
                                            Text(
                                                text = People.title,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp,
                                            )
                                            Text(
                                                text = People.description,
                                                fontWeight = FontWeight.Thin,
                                                fontSize = 14.sp,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    },
                )
            }
        }
    }
}
