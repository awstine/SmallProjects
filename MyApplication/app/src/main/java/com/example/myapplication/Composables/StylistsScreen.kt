@file:Suppress("ktlint:standard:package-name", "PreviewAnnotationInFunctionWithParameters")

package com.example.myapplication.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data3.stylistList

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
fun StylistsScreen(
    navController: NavController,
    // stylists: Stylists,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Stylists",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(stylistList) { stylist ->
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFDAE1E7),
                    modifier =
                        Modifier
                            .padding(10.dp)
                            .height(180.dp),
                    // shadowEevation = 10.dp
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .weight(2f),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "${stylist.firstName} ${stylist.lastName}",
                                fontSize = 24.sp,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = stylist.state,
                                fontSize = 14.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Normal,
                                modifier =
                                    Modifier
                                        .padding(start = 8.dp),
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            DatePicker()
                        }
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.size(width = 100.dp, height = 140.dp),
                        ) {
                            Image(
                                painter = painterResource(id = stylist.img),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun DatePicker() {
    val book by remember { mutableStateOf("Book") }
    val openDialog = remember { mutableStateOf(true) }

    Column {
        OutlinedButton(
            onClick = { openDialog.value = true },
            shape = RoundedCornerShape(16.dp),
            colors =
                ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                ),
            modifier = Modifier.size(width = 100.dp, height = 40.dp),
            elevation =
                ButtonDefaults.elevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp,
                ),
        ) {
            Text(
                text = book,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
            )
//
        }
    }
    if (openDialog.value) {
    }
}
