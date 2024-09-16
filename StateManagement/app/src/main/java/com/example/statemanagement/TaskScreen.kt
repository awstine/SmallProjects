package com.example.statemanagement

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("ktlint:standard:function-naming")
@Composable
fun TaskScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier,
    ) {
        WaterCounter()
        TaskScreen()
    }
}
