package com.example.myapplication.data

data class MenuDrawerItem2(
    // val icon: ImageVector,
    val title: String,
    //   val id: String,
    val badgeCount: Int,
    val hasBadge: Boolean,
    val onClick: () -> Unit,
)
