@file:Suppress("ktlint:standard:filename")

package com.example.myapplication.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Hair

@Suppress("ktlint:standard:class-naming")
class FavouritesViewModel : ViewModel() {
    @Suppress("ktlint:standard:backing-property-naming")
    private val _favourites = mutableStateListOf<Hair>()
    val favourites: List<Hair> get() = _favourites

    fun addFavourite(hair: Hair) {
        if (!_favourites.contains(hair)) {
            _favourites.add(hair)
        }
    }

    fun removeFavourite(hair: Hair) {
        _favourites.remove(hair)
    }
}
