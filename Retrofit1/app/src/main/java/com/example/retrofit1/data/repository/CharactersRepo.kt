package com.example.retrofit1.data.repository

import com.example.retrofit1.data.api.CharacterApi
import javax.inject.Inject

class CharactersRepo @Inject constructor(

    //We inject the CharacterApi
    private val charactersApi: CharacterApi
) {
    //We call the getCharacters method from the CharacterApi
    suspend fun getCharacters() = charactersApi.getCharacters()
}
