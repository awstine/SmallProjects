package com.example.retrofit2.network

import com.example.retrofit2.model.Digimon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/api/digimon")
    suspend fun getDigimons(): List<Digimon>

    // ApiService instance which interface is created by Retrofit
    companion object{
        var apiService: ApiService? = null
        fun getInstance(): ApiService{
            if(apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("https://digimon-api.vercel.app/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}
