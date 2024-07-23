@file:Suppress("ktlint:standard:filename")

package com.example.counterappstatemanagement

@Suppress("ktlint:standard:class-naming")
data class People(
    val image: Int,
    val title: String,
    val description: String,
)

val characters =
    listOf(
        People(
            R.drawable.belair,
            "Bianca Belair",
            "Am at the restaurant",
        ),
        People(
            R.drawable.jor,
            "Michael Jordan",
            "I'll call you when you're ready",
        ),
        People(
            R.drawable.james,
            "Lebron James",
            "Ssup?",
        ),
        People(
            R.drawable.hart,
            "Kevin Hart",
            "You're welcome",
        ),
        People(
            R.drawable.ronaldo,
            "Ronaldo",
            "You not in for Practice",
        ),
        People(
            R.drawable.belair,
            "Bianca Belair",
            "Wrestler",
        ),
        People(
            R.drawable.belair,
            "Bianca Belair",
            "Meet you at the Arenas",
        ),
        People(
            R.drawable.belair,
            "Bianca Belair",
            "Am at the restaurant",
        ),
        People(
            R.drawable.belair,
            "Bianca Belair",
            "Wrestler",
        ),
        People(
            R.drawable.belair,
            "Bianca Belair",
            "Wrestler",
        ),
    )
