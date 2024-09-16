package com.example.myapplication.data

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuDrawerItem(
    val icon: ImageVector,
    val title: String,
    //   val id: String,
    val badgeCount: Int,
    val hasBadge: Boolean,
    val onClick: () -> Unit,
)
