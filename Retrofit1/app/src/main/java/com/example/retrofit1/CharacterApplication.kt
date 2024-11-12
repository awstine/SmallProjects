package com.example.retrofit1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //This annotation is necessary to use Hilt
class CharacterApplication: Application() {
}