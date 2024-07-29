@file:Suppress("ktlint:standard:package-name", "PreviewAnnotationInFunctionWithParameters")

package com.example.myapplication.Composables

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import java.sql.Date
import java.text.SimpleDateFormat

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

                            DialogBox()
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

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
@Preview
private fun DialogBox() {
    val openDialogOne = remember { mutableStateOf(false) }
    val text by remember { mutableStateOf("Book") }
    var dateResult by remember { mutableStateOf("Date Picker") }
    OutlinedButton(
        onClick = { openDialogOne.value = true },
        shape = RoundedCornerShape(35.dp),
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
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleLarge,
        )
    }
    if (openDialogOne.value) { // Check state of the dialog
        AlertDialog(
            modifier = Modifier.padding(12.dp),
            onDismissRequest = { openDialogOne.value = true },
            title = { Text(text = " My Dialog title") },
            text = { Text(text = "Are you sure you want exit") },
            confirmButton = {
                TextButton(onClick = { openDialogOne.value = false }) {
                    Text(text = "Confirm")
                }
            },
            shape = RoundedCornerShape(30.dp),
            dismissButton = {
                TextButton(onClick = { openDialogOne.value = false }) {
                    Text(text = "Cancel")
                }
            },
        )
    }

    if (openDialogOne.value) {
        val datePickerState = rememberDatePickerState()
        val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
        val confirmEnabled = derivedStateOf { datePickerState.displayMode != null }

        DatePickerDialog(
            onDismissRequest = { openDialogOne.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialogOne.value = false
                        var date = "No Selection"
                        if (datePickerState.selectedDateMillis != null) {
                            date = Tools.convertToTime(datePickerState.selectedDateMillis!!)
                        }
                        dateResult = date
                    },
                    enabled = confirmEnabled.value,
                ) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialogOne.value = false
                    },
                ) {
                    Text(text = "Cancel")
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

class Tools {
    companion object {
        fun openLink(
            eContext: Context,
            url: String,
        ) {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(url)
            eContext.startActivity(openUrl)
        }

        fun convertToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("dd MMM YYYY")
            return format.format(date)
        }
    }
}
