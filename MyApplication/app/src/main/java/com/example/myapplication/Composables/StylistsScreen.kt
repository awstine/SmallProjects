@file:Suppress("ktlint:standard:package-name", "PreviewAnnotationInFunctionWithParameters")
@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.example.myapplication.Composables

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data3.DatePickerClass
import com.example.myapplication.data3.stylistList
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun StylistsScreen(
    navController: NavController,
    // stylists: Stylists,
) {
    val sheetState =
        rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed,
        )
    val scaffoldState =
        rememberBottomSheetScaffoldState(
            bottomSheetState = sheetState,
        )
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(900.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Text("Bottom sheet", fontSize = 60.sp)
            }
        },
        sheetBackgroundColor = Color.Cyan,
        sheetPeekHeight = 0.dp,
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
                        // shadowElevation = 10.dp
                    ) {
                        Row(
                            modifier =
                                Modifier.padding(16.dp).clickable {
                                    scope.launch {
                                        if (sheetState.isCollapsed) {
                                            sheetState.expand()
                                        } else {
                                            sheetState.collapse()
                                        }
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxSize()
                                        .weight(2f),
                                verticalArrangement = Arrangement.Center,
                            ) {
                                // Name of the stylist
                                Text(
                                    text = "${stylist.firstName} ${stylist.lastName}",
                                    fontSize = 24.sp,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.SemiBold,
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                // if he is occupied or not
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

                                // Dialog box for booking
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    DatePickerBox()
                                    TimePickerBox()
                                }
                            }

                            // Image
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun DatePickerBox() {
    val openConfirmDialog = remember { mutableStateOf(false) }
    val openDatePickerDialog = remember { mutableStateOf(false) }
    var bookedDate by remember { mutableStateOf("Book") }

    OutlinedButton(
        onClick = { openConfirmDialog.value = true },
        shape = RoundedCornerShape(35.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier.size(width = 120.dp, height = 40.dp),
        // elevation = 8.dp
    ) {
        Text(
            text = bookedDate,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleLarge,
        )
    }

    // Confirmation dialog
    if (openConfirmDialog.value) {
        AlertDialog(
            onDismissRequest = { openConfirmDialog.value = false },
            title = { Text(text = "Book Appointment") },
            text = { Text(text = "Do you want to book an appointment?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        openConfirmDialog.value = false
                        openDatePickerDialog.value = true
                    },
                ) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { openConfirmDialog.value = false }) {
                    Text(text = "No")
                }
            },
        )
    }

    // Date picker dialog
    if (openDatePickerDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }

        DatePickerDialog(
            onDismissRequest = { openDatePickerDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDatePickerDialog.value = false
                        var date = "No Selection"
                        datePickerState.selectedDateMillis?.let {
                            date = DatePickerClass.convertToTime(it)
                        }
                        bookedDate = date
                    },
                    enabled = confirmEnabled.value,
                ) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDatePickerDialog.value = false }) {
                    Text(text = "Cancel")
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun TimePickerBox() {
    var showTimePicker by remember { mutableStateOf(false) }
    var finalTime by remember { mutableStateOf("") }
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    val snackbarState = remember { SnackbarHostState() }
    val snackbarScope = rememberCoroutineScope()
    val calendar = Calendar.getInstance()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showTimePicker = true }) {
                Text(text = "Set Time")
            }

            if (showTimePicker) {
                TimePickerDialog(
                    onCancel = { showTimePicker = false },
                    onConfirm = { hour, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hour)
                        calendar.set(Calendar.MINUTE, minute)
                        finalTime = formatter.format(calendar.time)
                        snackbarScope.launch {
                            snackbarState.showSnackbar("Entered Time: $finalTime")
                        }
                        showTimePicker = false
                    },
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Set Time: $finalTime")
        }

        SnackbarHost(snackbarState)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun TimePickerDialog(
    onCancel: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit,
) {
    var hour by remember { mutableStateOf(12) }
    var minute by remember { mutableStateOf(0) }

    AlertDialog(
        onDismissRequest = onCancel,
        confirmButton = {
            TextButton(onClick = {
                onConfirm(hour, minute)
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancel")
            }
        },
        title = {
            Text("Select Time")
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    NumberPicker(
                        value = hour,
                        onValueChange = { hour = it },
                        range = 0..23,
                        label = { "$it" },
                    )
                    Text(text = " : ")
                    NumberPicker(
                        value = minute,
                        onValueChange = { minute = it },
                        range = 0..59,
                        label = { if (it < 10) "0 $it" else " $it" },
                    )
                }
            }
        },
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun NumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
    label: @Composable (Int) -> String,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .width(60.dp)
                .padding(16.dp),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                if (value < range.last) onValueChange(value + 1)
            }) {
                Text(text = "+")
            }
            Text(text = label(value))
            Button(onClick = {
                if (value > range.first) onValueChange(value - 1)
            }) {
                Text(text = "-")
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun StylistBottomSheet(navController: NavController) {
}
