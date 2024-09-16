@file:Suppress("ktlint:standard:no-empty-file")

package com.example.viewmodel.design

data class GameUiState(
    val currentScrambled: String = "",
    val currentWordCount: Int = 1,
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val isGameOver: Boolean = false,
)
