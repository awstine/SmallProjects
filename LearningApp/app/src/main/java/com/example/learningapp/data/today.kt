package com.example.learningapp.data

import com.example.learningapp.R

data class today (
    val bookmarkImage: Int,
    val img: Int,
    val title: String,
    val description: String,
    val name: String,
    val lesson: String

)

val todayList = listOf(
    today(
        bookmarkImage = R.drawable.img_1,
        img = R.drawable.img,
        title = "Lesson 1",
        description = "Learn about internet",
        name = "By John Doe",
        lesson = "Start Lesson"
    ),
    today(
        bookmarkImage = R.drawable.img_1,
        img = R.drawable.img_5,
        title = "Lesson 2",
        description = "Learn Kotlin",
        name = "By Sam Mill",
        lesson = "Start Lesson"
    ),
    today(
        bookmarkImage = R.drawable.img_1,
        img = R.drawable.img_4,
        title = "Lesson 3",
        description = "Learn Java",
        name = "By Jimmie Vill",
        lesson = "Start Lesson"
    ),
    today(
        bookmarkImage = R.drawable.img_1,
        img = R.drawable.img_2,
        title = "Lesson 4",
        description = "Learn Python",
        name = "By Quincy Larson",
        lesson = "Start Lesson"
    ),
    today(
        bookmarkImage = R.drawable.img_1,
        img = R.drawable.img_3,
        title = "Lesson 5",
        description = "Learn C++",
        name = "By Jannet Hol",
        lesson = "Start Lesson"
    ),
)
