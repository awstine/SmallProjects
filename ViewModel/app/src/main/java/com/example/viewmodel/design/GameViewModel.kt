@file:Suppress("ktlint:standard:no-empty-file")

package com.example.viewmodel.design

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viewmodel.data.MAX_NO_OF_WORDS
import com.example.viewmodel.data.SCORE_INCREASE
import com.example.viewmodel.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    @Suppress("ktlint:standard:backing-property-naming")
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set

    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()

    init {
        resetGame()
    }

    private fun updateGameState(updatedScore:Int){
        if (usedWords.size == MAX_NO_OF_WORDS){
            //if game over dont pick new word
          _uiState.update { currentState ->
              currentState.copy(
                  isGuessedWordWrong = false,
                  score = updatedScore,
                  isGameOver = true
              )
          }
        }else{
        _uiState.update { currentState ->
            currentState.copy(
                isGuessedWordWrong = false,
                currentScrambled = pickRandomWordAndShuffle(),
                score = updatedScore,
                currentWordCount = currentState.currentWordCount.inc()
            )
        }}
    }

    fun skipWord(){
        updateGameState(_uiState.value.score)
        //reset user guess
        updateUserGuess("")
    }

    fun checkUserGuess(){
        if (userGuess.equals(currentWord, ignoreCase = true)){
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        }else{
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }

        }
        //reset  what user guessed
        updateUserGuess("")

    }

    fun updateUserGuess(guessWord: String){
        userGuess = guessWord
    }

    fun resetGame(){
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambled = pickRandomWordAndShuffle())
    }

    private fun shuffleCurrentWord(word:String):String {
        val tempWord = word.toCharArray()
        //scrample the word
        tempWord.shuffle()
        while (String(tempWord).equals(word)){
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    private fun pickRandomWordAndShuffle(): String{
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)){
            return pickRandomWordAndShuffle()
        }else{
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }
}

