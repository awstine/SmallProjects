package com.example.retrofit1.data.api

import retrofit2.http.GET
import com.example.retrofit1.data.api.model.Character

interface CharacterApi {

    //Retrofit does everything for us, we just need to define the endpoint
    @GET(ApiConstants.ENDPOINT_CHARACTERS)
    suspend fun getCharacters(): List<Character>
}