package com.example.retrofit1.data.di

//import retrofit2.converter.moshi.MoshiConverterFactory
import com.example.retrofit1.data.api.ApiConstants
import com.example.retrofit1.data.api.CharacterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterApiModule {

    //We create a function that returns the CharacterApi...Tells hilt what to do
    @Provides
    @Singleton //We want to create only one instance of this and reduce memory usage
    fun provideApi(builder: Retrofit.Builder): CharacterApi {
        return builder
            .build()
            .create(CharacterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }

}