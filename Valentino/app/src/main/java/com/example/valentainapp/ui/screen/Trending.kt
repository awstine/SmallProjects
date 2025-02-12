package com.example.valentainapp.ui.screen

data class Trending(
        val image: Int
)

val trendingList = listOf(
        Trending(image = com.example.valentainapp.R.drawable.img1),
        Trending(image = com.example.valentainapp.R.drawable.img2),
        Trending(image = com.example.valentainapp.R.drawable.img3),
        Trending(image = com.example.valentainapp.R.drawable.img4),
        Trending(image = com.example.valentainapp.R.drawable.img5),
)

data class Romantic(
        val image: Int,
        val title: String,
        val subtitle: String
)

val romanticList = listOf(
        Romantic(
                image = com.example.valentainapp.R.drawable.img6,
                title = "Romantic",
                subtitle = "Romantic"
        ),
        Romantic(
                image = com.example.valentainapp.R.drawable.img7,
                title = "Romantic",
                subtitle = "Romantic"
        ),
        Romantic(
                image = com.example.valentainapp.R.drawable.img8,
                title = "Romantic",
                subtitle = "Romantic"
        ),
        Romantic(
                image = com.example.valentainapp.R.drawable.img9,
                title = "Romantic",
                subtitle = "Romantic"
        ),
        Romantic(
                image = com.example.valentainapp.R.drawable.img10,
                title = "Romantic",
                subtitle = "Romantic"
        ),
        Romantic(
                image = com.example.valentainapp.R.drawable.img11,
                title = "Romantic",
                subtitle = "Romantic"
        ), Romantic(
                image = com.example.valentainapp.R.drawable.img12,
                title = "Romantic",
                subtitle = "Romantic"
        ),
)
